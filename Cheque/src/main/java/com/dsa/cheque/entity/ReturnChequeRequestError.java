package com.dsa.cheque.entity;

import com.dsa.core.entity.base.AbstractEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "RETURNCHEQUECBIREQUESTERROR")
@SequenceGenerator(name = "SEQ_DB_DEFAULT", sequenceName = "SEQ_CBI28XREPSENTERRORLOG", allocationSize = 1)
public class ReturnChequeRequestError extends AbstractEntity {

    @Column(name = "REQID")
    private long reqId;
    @Column(name = "ISJOBREQ")
    private String isJobRequest;
    @Column(name = "ERRORCODE")
    private String errorCode;

    @Column(name = "RECIEVEDATE")
    private Date receiveDate;

    @Column(name = "DESCRIPTION")
    private String description;

    public long getReqId() {
        return reqId;
    }

    public void setReqId(long reqId) {
        this.reqId = reqId;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsJobRequest() {
        return isJobRequest;
    }

    public void setIsJobRequest(String isJobRequest) {
        this.isJobRequest = isJobRequest;
    }
}
