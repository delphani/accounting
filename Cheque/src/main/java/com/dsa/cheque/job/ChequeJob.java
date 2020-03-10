package com.dsa.cheque.job;

import com.dsa.core.exception.CoreException;
import com.dsa.core.jobs.AbstractConfigJob;
import com.dsa.core.repository.job.SubSystemJobConfigsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;

public abstract class ChequeJob extends AbstractConfigJob {
    protected static final Logger log = LoggerFactory.getLogger(ChequeJob.class);

    @Value("${cheque.job.is.enable}") private String returnChequeJobIsEnable;
    private static final String NO = "N";
    @Autowired
    protected ApplicationContext applicationContext;
    protected ChequeJob(String jobKey, SubSystemJobConfigsRepository repository) {
        super(jobKey, repository);
    }

    private boolean isEnable() {
        return (this.returnChequeJobIsEnable == null || this.returnChequeJobIsEnable.equals("") || this.returnChequeJobIsEnable.equalsIgnoreCase(NO)) == false;
    }

    @Override
    public void run() throws CoreException {
        try {
            if(super.jobConfig.isActive() && this.isEnable()) {
                log.info("************************************************************");
                log.info(String.format("Job Group Name: %s \t Job Name: %s, Started", this.getGroupName(), this.getJobName()));
                log.info("************************************************************");

                this.execute();
            }
        } catch (Throwable ex) {
            log.error(ex.getMessage(), ex);
        }
    }
}
