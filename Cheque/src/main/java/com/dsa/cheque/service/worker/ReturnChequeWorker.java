package com.dsa.cheque.service.worker;

import com.dsa.cheque.entity.ReturnChequeRequestError;
import com.dsa.cheque.entity.ReturnChequeRequestJob;
import com.dsa.cheque.repository.ReturnChequeRequestJobRepository;
import com.dsa.cheque.service.ReturnChequeService;
import com.dsa.cheque.utiles.LogUtil;
import com.dsa.core.worker.AbstractWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public abstract class ReturnChequeWorker extends AbstractWorker {
    private static Logger logger = LoggerFactory.getLogger(ReturnChequeWorker.class);

    @Autowired
    ReturnChequeRequestJobRepository jobRepository;
    @Autowired
    protected ReturnChequeService chequeService;

    protected void rowLockup(String workerId, int count) {
        jobRepository.rowJobLockup(workerId, count);
    }

    protected List<ReturnChequeRequestJob> readRow(String workerId, String status) {
        return jobRepository.findByWorkerIdAndStatus(workerId, status);
    }

    protected void rollBack(ReturnChequeRequestJob jobReq, String errorCode, String msg) {
        jobRepository.updateStatusById(jobReq.getId(), ReturnChequeService.REJECTED, jobReq.getSendDate(), getCurrntDate());
        createManualErr(errorCode, msg, jobReq.getId());
    }

    protected void rollBackRowLocked(String workerId, Throwable throwable) {
        logger.error(String.format("Rollback all req.."));
        jobRepository.updateStatusByWorkerId(workerId, ReturnChequeService.REJECTED);
        List<ReturnChequeRequestJob> requestJobs = jobRepository.findByWorkerIdAndStatus(workerId, ReturnChequeService.ENTRY);
        requestJobs.forEach(req -> {
            createManualErr(LogUtil.CODE_SERVICE_INTERNAL_ERROR, throwable.getMessage(), req.getId());
        });
    }

    protected boolean newRequestForTryAgain(ReturnChequeRequestJob requestJob) {
        try {
            int countRowEntryStatus = jobRepository.countByRequestIdAndStatus(requestJob.getRequestId(), ReturnChequeService.ENTRY);
            if (countRowEntryStatus == 0) {
                ReturnChequeRequestJob newReq = (ReturnChequeRequestJob) requestJob.clone();
                newReq.init();
                newReq.setStatus(ReturnChequeService.ENTRY);
                jobRepository.save(newReq);
                return true;
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected void createManualErr(String code, String msg, long reqId) {
        ReturnChequeRequestError requestError = new ReturnChequeRequestError();
        requestError.setErrorCode(code);
        requestError.setDescription(msg);
        requestError.setReqId(reqId);
        requestError.setReceiveDate(new Date(System.currentTimeMillis()));
        requestError.setIsJobRequest("Y");
        chequeService.saveRequestError(requestError);
    }

    protected Date getCurrntDate() {
        return new Date(System.currentTimeMillis());
    }
}
