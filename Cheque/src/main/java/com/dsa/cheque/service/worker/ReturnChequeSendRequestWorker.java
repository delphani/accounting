package com.dsa.cheque.service.worker;

import com.dsa.cheque.client.returncheque.Error;
import com.dsa.cheque.client.returncheque.IChequeService;
import com.dsa.cheque.client.returncheque.StrResponse;
import com.dsa.cheque.entity.ReturnChequeCbiError;
import com.dsa.cheque.entity.ReturnChequeRequestError;
import com.dsa.cheque.entity.ReturnChequeRequestJob;
import com.dsa.cheque.service.ReturnChequeService;
import com.dsa.cheque.utiles.ReturnChequeServiceFactory;
import com.dsa.cheque.utiles.LogUtil;
import com.dsa.cheque.ws.service.ReturnChequeClientService;
import com.dsa.core.log.ObjectLogger;
import javassist.tools.rmi.RemoteException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.xml.rpc.ServiceException;
import java.util.Date;
import java.util.List;

import static com.dsa.cheque.utiles.LogUtil.*;

@Service
@Scope("prototype")
public class ReturnChequeSendRequestWorker extends ReturnChequeWorker {
    private static final Logger logger = LoggerFactory.getLogger(ReturnChequeClientService.class);

    @Autowired
    private ReturnChequeServiceFactory serviceFactory;


    private IChequeService serviceInstance() {
        return serviceFactory.create();
    }

    @Override
    public synchronized void execute() {
        try {
            rowLockup(workerId, records);
            List<ReturnChequeRequestJob> requestJobList = readRow(workerId, ReturnChequeService.PENDING);
            requestJobList.forEach(jReq -> {
                String requestSendId = jReq.getRequestId();
                try {
                    logger.info(LogUtil.getLogVal(JOB_NEW_REQUEST, requestSendId));
                    String requestStr = getReturnChequeCbiText(requestSendId);
                    StrResponse response = serviceInstance().sendChequeNewRecord(requestStr);
                    jReq.setSendDate(getCurrntDate());
                    String responseStatus = ReturnChequeService.SENT;
                    if (response.getHasError()) {
                        if (isDuplicateRequest(response)) {
                            chequeService.updateRejectionRefNo(requestSendId, response.getReturnValue());
                            logger.warn(String.format(JOB_GET_TRACE_CODE, requestSendId, EXCEPTION_ALREADY_DESC_ERROR));
                        } else {
                            logger.error(LogUtil.getLogVal(JOB_GET_TRACE_CODE, requestSendId, PROP_CBI_ERROR));
                            responseStatus = ReturnChequeService.REJECTED;
                            this.insertRequestError(response.getErrors(), jReq.getId(), requestSendId);
                            checkAutoSendMsg(jReq, response);
                        }
                    } else {
                        chequeService.updateRejectionRefNo(requestSendId, response.getReturnValue());
                        logger.info(String.format(JOB_GET_TRACE_CODE, requestSendId, PROP_SUCCESS));
                    }
                    jReq.setStatus(responseStatus);
                    jReq.setResponseDate(getCurrntDate());
                    jReq.setRefNo(response.getReturnValue());
                    jobRepository.save(jReq);
                    Thread.sleep(100L);
                } catch (Throwable throwable) {
                    String errorCd = CODE_SERVICE_INTERNAL_ERROR;
                    String errorDsc = throwable.getMessage();
                    if (throwable instanceof RemoteException) {
                        errorCd = CODE_SERVICE_NOT_AVILABLE_ERROR;
                        errorDsc = MSG_CBI_SERVICE_NOT_AVAILABLE;
                        newRequestForTryAgain(jReq);
                    }
                    rollBack(jReq, errorCd, errorDsc);
                    throwable.printStackTrace();
                }

            });
        } catch (Throwable throwable) {
            rollBackRowLocked(workerId, throwable);
            throwable.printStackTrace();
        }
    }

    private boolean isDuplicateRequest(StrResponse response) {
        return response.getHasError() && (response.getReturnValue() != null && !response.getReturnValue().equals(""));
    }

    private String getReturnChequeCbiText(String reqId) throws Exception {
        String returnChequeVal = chequeService.generateReturnChequeText(reqId);
        if (returnChequeVal == null || returnChequeVal.equals("")) {
            throw new Exception(LogUtil.getLogVal(EXCEPTION_FORMAT_DESC_ERROR, reqId));
        }
        logger.info(LogUtil.getLogVal(JOB_REQUEST_VALUE, reqId, returnChequeVal));
        return returnChequeVal;
    }

    private void insertRequestError(Error[] errors, long reqId, String requestSendId) {
        if (errors != null) {
            int indx = 1;
            for (Error error : errors) {
                createManualErr(error.getErrorCd(), error.getErrorDsc(), reqId);
                logger.error(String.format("[%s] Err-%s  %s:%s", requestSendId, indx++, error.getErrorCd(), error.getErrorDsc()));
            }
        }
    }

    private boolean errorAutoSent(Error[] errors) {
        if (errors != null) {
            for (Error error : errors) {
                ReturnChequeCbiError rErr = chequeService.findErrorBycode(error.getErrorCd());
                if (rErr != null && rErr.isAutoSent())
                    return true;
            }
        }
        return false;
    }

    private void checkAutoSendMsg(ReturnChequeRequestJob jReq, StrResponse response) {
        if (errorAutoSent(response.getErrors())) {
            boolean inserted = newRequestForTryAgain(jReq);
            if (inserted)
                logger.warn(LogUtil.getLogVal(JOB_CREATE_NEW_REQUEST_FOR_TRY_AGAIN, jReq.getRequestId()));
        }
    }
}
