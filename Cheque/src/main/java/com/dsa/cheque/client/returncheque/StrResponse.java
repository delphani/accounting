/**
 * StrResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.dsa.cheque.client.returncheque;

public class StrResponse  implements java.io.Serializable {
    private Error[] errors;

    private Boolean hasError;

    private Error[] oldFormatErrors;

    private String returnValue;

    public StrResponse() {
    }

    public StrResponse(
           Error[] errors,
           Boolean hasError,
           Error[] oldFormatErrors,
           String returnValue) {
           this.errors = errors;
           this.hasError = hasError;
           this.oldFormatErrors = oldFormatErrors;
           this.returnValue = returnValue;
    }


    /**
     * Gets the errors value for this StrResponse.
     *
     * @return errors
     */
    public Error[] getErrors() {
        return errors;
    }


    /**
     * Sets the errors value for this StrResponse.
     *
     * @param errors
     */
    public void setErrors(Error[] errors) {
        this.errors = errors;
    }


    /**
     * Gets the hasError value for this StrResponse.
     *
     * @return hasError
     */
    public Boolean getHasError() {
        return hasError;
    }


    /**
     * Sets the hasError value for this StrResponse.
     *
     * @param hasError
     */
    public void setHasError(Boolean hasError) {
        this.hasError = hasError;
    }


    /**
     * Gets the oldFormatErrors value for this StrResponse.
     *
     * @return oldFormatErrors
     */
    public Error[] getOldFormatErrors() {
        return oldFormatErrors;
    }


    /**
     * Sets the oldFormatErrors value for this StrResponse.
     *
     * @param oldFormatErrors
     */
    public void setOldFormatErrors(Error[] oldFormatErrors) {
        this.oldFormatErrors = oldFormatErrors;
    }


    /**
     * Gets the returnValue value for this StrResponse.
     *
     * @return returnValue
     */
    public String getReturnValue() {
        return returnValue;
    }


    /**
     * Sets the returnValue value for this StrResponse.
     *
     * @param returnValue
     */
    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof StrResponse)) return false;
        StrResponse other = (StrResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.errors==null && other.getErrors()==null) ||
             (this.errors!=null &&
              java.util.Arrays.equals(this.errors, other.getErrors()))) &&
            ((this.hasError==null && other.getHasError()==null) ||
             (this.hasError!=null &&
              this.hasError.equals(other.getHasError()))) &&
            ((this.oldFormatErrors==null && other.getOldFormatErrors()==null) ||
             (this.oldFormatErrors!=null &&
              java.util.Arrays.equals(this.oldFormatErrors, other.getOldFormatErrors()))) &&
            ((this.returnValue==null && other.getReturnValue()==null) ||
             (this.returnValue!=null &&
              this.returnValue.equals(other.getReturnValue())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getErrors() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getErrors());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getErrors(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getHasError() != null) {
            _hashCode += getHasError().hashCode();
        }
        if (getOldFormatErrors() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getOldFormatErrors());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getOldFormatErrors(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getReturnValue() != null) {
            _hashCode += getReturnValue().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(StrResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/Cheque.DataContract", "StrResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errors");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/Cheque.DataContract", "Errors"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/Cheque.DataContract", "Error"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/Cheque.DataContract", "Error"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hasError");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/Cheque.DataContract", "HasError"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("oldFormatErrors");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/Cheque.DataContract", "OldFormatErrors"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/Cheque.DataContract", "Error"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/Cheque.DataContract", "Error"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("returnValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/Cheque.DataContract", "ReturnValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
