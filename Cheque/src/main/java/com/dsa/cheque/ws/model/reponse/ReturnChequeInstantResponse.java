package com.dsa.cheque.ws.model.reponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReturnChequeInstantResponse {
    private String returnValue;
    private boolean hasError;
    @XmlElementWrapper(name = "errors")
    @XmlElement(name = "error")
    private ResponseError[] errors;

    public String getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public ResponseError[] getErrors() {
        return errors;
    }

    public void setErrors(ResponseError[] errors) {
        this.errors = errors;
    }
}
