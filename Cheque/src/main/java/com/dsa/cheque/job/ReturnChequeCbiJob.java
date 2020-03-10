package com.dsa.cheque.job;

import com.dsa.cheque.service.worker.ReturnChequeSendRequestWorker;
import com.dsa.core.entity.WorkerConfigEntity;
import com.dsa.core.exception.CoreException;
import com.dsa.core.jobs.annotation.BankJob;
import com.dsa.core.repository.WorkerConfigRepository;
import com.dsa.core.repository.job.SubSystemJobConfigsRepository;
import org.springframework.beans.factory.annotation.Autowired;

@BankJob(ReturnChequeCbiJob.JOB_KEY)
public class ReturnChequeCbiJob extends ChequeJob  {
    public final static String JOB_KEY = "CALL_RETURN_CHEQUE_CBI";

    @Autowired
    private WorkerConfigRepository workerConfigRepository;
    protected ReturnChequeCbiJob(SubSystemJobConfigsRepository repository) {
        super(JOB_KEY, repository);
    }

    @Override
    public void execute() throws CoreException {
        WorkerConfigEntity workerConfig = this.workerConfigRepository.findByJobKey(super.jobConfig.getJobKey());
        ReturnChequeSendRequestWorker worker = super.applicationContext.getBean(ReturnChequeSendRequestWorker.class);
        worker.initialize(super.getJobName(), workerConfig.getRecords());
        worker.run();
    }
}
