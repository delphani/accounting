package com.dsa.cheque.entity;

import com.dsa.core.entity.base.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "RETURNCHEQUECBIREQUESTJOB")
@Entity
@SequenceGenerator(name="SEQ_DB_DEFAULT", sequenceName="DD_STATEMENTDTLS_SEQ", allocationSize=100)
public class ReturnChequeRequestJob extends AbstractEntity implements Cloneable{
    @Column(name = "REQUESTSENDID")
    private String requestId;
    @Column(name = "WORKERID")
    private String workerId;
    @Column(name = "CREATE_DATE")
    private Date createDate;
    @Column(name = "SEND_DATE")
    private Date sendDate;
    @Column(name = "RESPONSE_DATE")
    private Date responseDate;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "CBIREFNO")
    private String refNo;

    public void init() {
        this.setId(null);
        this.setWorkerId(null);
        this.setSendDate(null);
        this.setResponseDate(null);
    }
    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Date getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Date responseDate) {
        this.responseDate = responseDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
