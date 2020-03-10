package com.dsa.cheque.entity;

import com.dsa.core.entity.base.AbstractEntity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "RETURNCHEQUECBIREQUEST")
@Entity
@SequenceGenerator(name="SEQ_DB_DEFAULT", sequenceName="DD_STATEMENTDTLS_SEQ", allocationSize=100)
public class ReturnChequeRequest extends AbstractEntity {
    @Column(name = "BANK")
    private String bank;
    @Column(name = "BRANCH")
    private String branch;
    @Column(name = "REQUESTSENDID")
    private String requestId;
    @Column(name = "SEND_DATE")
    private Date sendDate;
    @Column(name = "RESPONSE_DATE")
    private Date responseDate;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "ENTRYUSER")
    private String userId;
    @Column(name = "CBIREFNO")
    private String refNo;

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }
}
