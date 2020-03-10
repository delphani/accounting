/**
 * ChequeService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.dsa.cheque.client.returncheque;

public interface ChequeService extends javax.xml.rpc.Service {
    public String getBasicHttpBinding_IChequeServiceAddress();

    public IChequeService getBasicHttpBinding_IChequeService() throws javax.xml.rpc.ServiceException;

    public IChequeService getBasicHttpBinding_IChequeService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;

    public IChequeService getBasicHttpBinding_IChequeService(java.net.URL portAddress, String username, String password, int timeout) throws javax.xml.rpc.ServiceException;
}
