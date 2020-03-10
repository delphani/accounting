package com.dsa.cheque.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "RETURNCHEQUESCBI")
@Entity
public class ReturnChequeCbi {
    @Id
    @Column(name = "REQUESTSENDID")
    private String requestId;
    @Column(name = "CHEQUENO")
    private String chequeId;
    @Column(name = "REJECTIONREFNO")
    private String rejectionRefNo;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getChequeId() {
        return chequeId;
    }

    public void setChequeId(String chequeId) {
        this.chequeId = chequeId;
    }

    public String getRejectionRefNo() {
        return rejectionRefNo;
    }

    public void setRejectionRefNo(String rejectionRefNo) {
        this.rejectionRefNo = rejectionRefNo;
    }
}
