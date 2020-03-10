package com.dsa.cheque.ws.model.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@Builder
public class ResponseError {
    private String errorCode;
    private String errorDesc;

    public ResponseError(){

    }
    public ResponseError(String code, String message) {
        this.setErrorCode(code);
        this.setErrorDesc(message);
    }
    public static ResponseError createError(String code, String message) {
        return new ResponseError(code, message);
    }
    public static ResponseError[] createErrors(String code, String message) {
        ResponseError[] responseErrors = new ResponseError[1];
        responseErrors[0] = new ResponseError(code, message);
        return responseErrors;
    }
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }
}
