package com.dsa.cheque.ws.model.request;

import lombok.Data;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlType(name="")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class ReturnChequeInstantRequest {
    @XmlElement(required = true)
    private String bank;
    @XmlElement(required = true)
    private String branch;
    @XmlElement(required = true)
    private String requestId;
    @XmlElement(required = true)
    private String userCode;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

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
}
