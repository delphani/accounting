package com.dsa.cheque.job;

import com.dsa.cheque.service.ReturnChequeService;
import com.dsa.core.exception.CoreException;
import com.dsa.core.jobs.annotation.BankJob;
import com.dsa.core.repository.job.SubSystemJobConfigsRepository;
import org.springframework.beans.factory.annotation.Autowired;

@BankJob(RetuenChequeClrJob.JOB_KEY)
public class RetuenChequeClrJob extends ChequeJob {
    public final static String JOB_KEY = "CALL_CLRRETURN_CHEQUE_CBI";

    @Autowired
    ReturnChequeService returnChequeService;

    protected RetuenChequeClrJob(SubSystemJobConfigsRepository repository) {
        super(JOB_KEY, repository);
    }

    @Override
    public void execute() throws CoreException {
        try {
            log.info("Generate a request for return cheque from clr");
            this.returnChequeService.generateRequestForReturnChequeFromClr();
        } catch (Throwable throwable) {
            log.error("Generate clr request failed");
            throwable.printStackTrace();
        }
    }

}
