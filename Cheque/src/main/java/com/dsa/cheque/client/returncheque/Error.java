/**
 * Error.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.dsa.cheque.client.returncheque;

public class Error  implements java.io.Serializable {
    private String errorCd;

    private String errorDsc;

    public Error() {
    }

    public Error(
           String errorCd,
           String errorDsc) {
           this.errorCd = errorCd;
           this.errorDsc = errorDsc;
    }


    /**
     * Gets the errorCd value for this Error.
     *
     * @return errorCd
     */
    public String getErrorCd() {
        return errorCd;
    }


    /**
     * Sets the errorCd value for this Error.
     *
     * @param errorCd
     */
    public void setErrorCd(String errorCd) {
        this.errorCd = errorCd;
    }


    /**
     * Gets the errorDsc value for this Error.
     *
     * @return errorDsc
     */
    public String getErrorDsc() {
        return errorDsc;
    }


    /**
     * Sets the errorDsc value for this Error.
     *
     * @param errorDsc
     */
    public void setErrorDsc(String errorDsc) {
        this.errorDsc = errorDsc;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof Error)) return false;
        Error other = (Error) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.errorCd==null && other.getErrorCd()==null) ||
             (this.errorCd!=null &&
              this.errorCd.equals(other.getErrorCd()))) &&
            ((this.errorDsc==null && other.getErrorDsc()==null) ||
             (this.errorDsc!=null &&
              this.errorDsc.equals(other.getErrorDsc())));
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
        if (getErrorCd() != null) {
            _hashCode += getErrorCd().hashCode();
        }
        if (getErrorDsc() != null) {
            _hashCode += getErrorDsc().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Error.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/Cheque.DataContract", "Error"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorCd");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/Cheque.DataContract", "ErrorCd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorDsc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/Cheque.DataContract", "ErrorDsc"));
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
