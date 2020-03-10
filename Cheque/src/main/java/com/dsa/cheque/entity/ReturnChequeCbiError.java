package com.dsa.cheque.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.HashMap;
import java.util.Map;

@Table(name = "RETURNCHEQUECBIERRORS")
@Entity
public class ReturnChequeCbiError {
    @Id
    @Column(name = "ERROR_CODE")
    private String code;
    @Column(name = "ERROR_TYPE")
    private String type;
    @Column(name = "ERROR_DESC")
    private String msgKey;
    @Column(name = "JOB_AUTOSENT")
    private String jobAutoSent;

    public boolean isAutoSent() {
        if(this.getJobAutoSent()!=null && !this.getJobAutoSent().equals("") && this.getJobAutoSent().equals("Y"))
            return true;
        return false;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMsgKey() {
        return msgKey;
    }

    public void setMsgKey(String msgKey) {
        this.msgKey = msgKey;
    }

    public String getJobAutoSent() {
        return jobAutoSent;
    }

    public void setJobAutoSent(String jobAutoSent) {
        this.jobAutoSent = jobAutoSent;
    }
}
