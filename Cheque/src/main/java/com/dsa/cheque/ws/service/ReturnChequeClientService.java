package com.dsa.cheque.ws.service;

import com.dsa.cheque.client.returncheque.Error;
import com.dsa.cheque.client.returncheque.IChequeService;
import com.dsa.cheque.client.returncheque.StrResponse;
import com.dsa.cheque.entity.*;
import com.dsa.cheque.repository.ReturnChequeRequestJobRepository;
import com.dsa.cheque.service.ReturnChequeService;
import com.dsa.cheque.utiles.ReturnChequeServiceFactory;
import com.dsa.cheque.utiles.LogUtil;
import com.dsa.cheque.ws.model.reponse.ResponseError;
import com.dsa.cheque.ws.model.reponse.ReturnChequeInstantResponse;
import com.dsa.cheque.ws.model.request.ReturnChequeInstantRequest;
import com.dsa.core.exception.CoreException;
import com.dsa.core.log.ObjectLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import static com.dsa.cheque.utiles.LogUtil.JOB_CREATE_NEW_REQUEST_FOR_TRY_AGAIN;

@Service
public class ReturnChequeClientService {

    private static final Logger logger = LoggerFactory.getLogger(ReturnChequeClientService.class);
    private IChequeService cbiService;
    private ReturnChequeService chequeService;

    @Autowired
    public ReturnChequeClientService(ReturnChequeServiceFactory serviceFactory, ReturnChequeService chequeService) {
        this.cbiService = serviceFactory.create();
        this.chequeService = chequeService;

    }

    public ReturnChequeInstantResponse getRejectTraceCode(ReturnChequeInstantRequest request)
            throws Exception {
        ReturnChequeInstantResponse instantResponse = new ReturnChequeInstantResponse();
        this.validationRequest(request);

        logger.info(String.format(LogUtil.LOG_REQUEST, request.getBranch(), request.getUserCode(), request.getRequestId()));
        ReturnChequeRequest lastRequest = this.getLastSuccessRequest(request.getRequestId());
        if (lastRequest != null) {
            instantResponse.setHasError(false);
            instantResponse.setReturnValue(lastRequest.getRefNo());
            logger.warn(String.format(LogUtil.LOG_FETCH_REQUEST, request.getRequestId(), lastRequest.getRefNo()));
            return instantResponse;
        }
        String returnChequeRecord = getReturnChequeCbiText(request.getRequestId());
        ReturnChequeRequest requestEntity = this.insertRequest(request);
        if (returnChequeRecord == null || returnChequeRecord.equals("")) {
            this.updateRequestStatus(requestEntity.getId(), ReturnChequeService.REJECTED);
            String msg = String.format(LogUtil.EXCEPTION_FORMAT_DESC_ERROR, request.getRequestId());
            this.manualError(LogUtil.CODE_SERVICE_INVALID_REQUEST_ID, msg, requestEntity.getId());
            throw new CoreException(ReturnChequeService.fetchError(LogUtil.CODE_SERVICE_INVALID_REQUEST_ID));
        } else {
            logger.info(String.format(LogUtil.LOG_RETURN_CHEQUE_REC, returnChequeRecord));
            try {
                StrResponse response = this.cbiService.sendChequeNewRecord(returnChequeRecord);
                ObjectLogger.log(response);
                if (response.getHasError()) {
                    requestEntity.setStatus(ReturnChequeService.REJECTED);
                    if (response.getReturnValue() != null && !response.getReturnValue().equals("")) {
                        requestEntity.setRefNo(response.getReturnValue());
                        this.updateReturnChequeCbiRefNo(request.getRequestId(), response.getReturnValue());
                        this.manualError(LogUtil.CODE_CBI_DUPLICATE_REQUEST, LogUtil.EXCEPTION_ALREADY_DESC_ERROR, requestEntity.getId());
                        logger.warn(String.format("%s : [%s]", LogUtil.EXCEPTION_ALREADY_DESC_ERROR, request.getRequestId()));
                    } else {
                        logger.error(String.format("Response error [%s]:", request.getRequestId()));
                        this.insertRequestError(response.getErrors(), requestEntity.getId());
                        checkAutoSendMsg(requestEntity, response);
                    }
                } else {
                    requestEntity.setStatus(ReturnChequeService.SENT);
                    requestEntity.setRefNo(response.getReturnValue());
                    this.updateReturnChequeCbiRefNo(request.getRequestId(), response.getReturnValue());
                }
                requestEntity.setResponseDate(this.getCurrntDate());
                this.updateChequeRequest(requestEntity);
                instantResponse = mapCbiResponseToReturnCheque(response);
            } catch (RemoteException e) {
                logger.error(e.getMessage(), e);
                String errorDesc = ReturnChequeService.fetchError(LogUtil.CODE_SERVICE_NOT_AVILABLE_ERROR);
                this.updateRequestStatus(requestEntity.getId(), ReturnChequeService.REJECTED);
                this.manualError(LogUtil.CODE_SERVICE_NOT_AVILABLE_ERROR, errorDesc, requestEntity.getId());
                instantResponse.setHasError(true);
                instantResponse.setErrors(ResponseError.createErrors(LogUtil.CODE_SERVICE_NOT_AVILABLE_ERROR, errorDesc));
                newJobRequestForTryAgain(requestEntity);
            }
        }
        return instantResponse;
    }

    private void validationRequest(ReturnChequeInstantRequest request) {
        if (request.getBank() == null || request.getBank().equals("")) {
            throw new CoreException(LogUtil.CODE_SERVICE_NOT_EMPTY_PARAM,
                    ReturnChequeService.fetchError(LogUtil.CODE_SERVICE_NOT_EMPTY_PARAM));
        } else if (request.getBranch() == null || request.getBranch().equals("")) {
            throw new CoreException(LogUtil.CODE_SERVICE_NOT_EMPTY_PARAM,
                    ReturnChequeService.fetchError(LogUtil.CODE_SERVICE_NOT_EMPTY_PARAM));
        } else if (request.getRequestId() == null || request.getRequestId().equals("")) {
            throw new CoreException(LogUtil.CODE_SERVICE_NOT_EMPTY_PARAM,
                    ReturnChequeService.fetchError(LogUtil.CODE_SERVICE_NOT_EMPTY_PARAM));
        } else if (request.getUserCode() == null || request.getUserCode().equals("")) {
            throw new CoreException(LogUtil.CODE_SERVICE_NOT_EMPTY_PARAM,
                    ReturnChequeService.fetchError(LogUtil.CODE_SERVICE_NOT_EMPTY_PARAM));
        }
    }

    private ReturnChequeInstantResponse mapCbiResponseToReturnCheque(StrResponse response) {
        ReturnChequeInstantResponse instantResponse = new ReturnChequeInstantResponse();
        instantResponse.setReturnValue(response.getReturnValue());
        instantResponse.setHasError(response.getHasError());
        Error[] errors = response.getErrors();
        if (response.getHasError() && errors != null) {
            if (response.getReturnValue() != null && !response.getReturnValue().equals("")) {
                instantResponse.setHasError(false);
            } else {
                instantResponse.setErrors(this.mapCbiErrorToResponseError(errors));
            }
        }
        return instantResponse;
    }

    private ResponseError[] mapCbiErrorToResponseError(Error[] errors) {
        ResponseError[] responseErrors = null;
        if (errors != null) {
            responseErrors = new ResponseError[errors.length];
            int indx = 0;
            for (Error error : errors) {
                responseErrors[indx++] = new ResponseError(error.getErrorCd(), ReturnChequeService.fetchError(error.getErrorCd()));
            }
        }
        return responseErrors;
    }

    private ReturnChequeRequest insertRequest(ReturnChequeInstantRequest request) {
        ReturnChequeRequest entity = new ReturnChequeRequest();
        entity.setBank(request.getBank());
        entity.setBranch(request.getBranch());
        entity.setUserId(request.getUserCode());
        entity.setRequestId(request.getRequestId());
        entity.setSendDate(this.getCurrntDate());
        entity.setStatus(ReturnChequeService.PENDING);
        return chequeService.saveRequest(entity);
    }

    private ReturnChequeRequest updateChequeRequest(ReturnChequeRequest chequeRequest) {
        return chequeService.saveRequest(chequeRequest);
    }

    private ReturnChequeCbi getReturnChequeCbi(String reqId) {
        return chequeService.getReturnChequeCbi(reqId);
    }

    private void updateReturnChequeCbiRefNo(String reqId, String refNo) {
        chequeService.updateRejectionRefNo(reqId, refNo);
    }

    private void insertRequestError(Error[] errors, long reqId) {
        if (errors != null) {
            for (Error error : errors) {
                ReturnChequeRequestError requestError = new ReturnChequeRequestError();
                requestError.setErrorCode(error.getErrorCd());
                requestError.setDescription(error.getErrorDsc());
                requestError.setReqId(reqId);
                requestError.setIsJobRequest("N");
                requestError.setReceiveDate(new Date(System.currentTimeMillis()));
                chequeService.saveRequestError(requestError);
                logger.error(String.format("[%s] %s : %s", reqId, error.getErrorCd(), error.getErrorDsc()));
            }
        }
    }

    private void updateRequestStatus(long reqId, String status) {
        chequeService.updateRequestStatus(reqId, status);
    }

    private String getReturnChequeCbiText(String reqId) {
        String returnChequeVal = chequeService.generateReturnChequeText(reqId);
        return returnChequeVal;
    }

    private ReturnChequeRequest getLastSuccessRequest(String reqId) {
        return chequeService.findByRequesuAndStatus(reqId, ReturnChequeService.SENT);
    }

    private Date getCurrntDate() {
        return new Date(System.currentTimeMillis());
    }

    public ResponseError[] manualError(String code, String desc, long reqId) {
        Error[] errors = new Error[1];
        errors[0] = new Error();
        errors[0].setErrorCd(code);
        errors[0].setErrorDsc(desc);
        this.insertRequestError(errors, reqId);
        return mapCbiErrorToResponseError(errors);
    }

    protected void newJobRequestForTryAgain(ReturnChequeRequest request) {
        List res = chequeService.findByReqIdAndStatus(request.getRequestId(), ReturnChequeService.ENTRY);
        if(res!=null && res.size()>0) {
            logger.warn("A job request with status 'E' is available");
        }
        else {
            ReturnChequeRequestJob newReq = new ReturnChequeRequestJob();
            newReq.setRequestId(request.getRequestId());
            newReq.setCreateDate(getCurrntDate());
            newReq.setStatus(ReturnChequeService.ENTRY);
            chequeService.addNewRequestJob(newReq);
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

    private void checkAutoSendMsg(ReturnChequeRequest request, StrResponse response) {
        if (errorAutoSent(response.getErrors())) {
            int countEntryRow = chequeService.countRequestJob(request.getRequestId(), ReturnChequeService.ENTRY);
            if(countEntryRow==0) {
                newJobRequestForTryAgain(request);
                logger.warn(LogUtil.getLogVal(JOB_CREATE_NEW_REQUEST_FOR_TRY_AGAIN, request.getRequestId()));
            }
        }
    }
}
