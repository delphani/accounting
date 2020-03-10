/**
 * ChequeServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.dsa.cheque.client.returncheque;

public class ChequeServiceLocator extends org.apache.axis.client.Service implements ChequeService {

    public ChequeServiceLocator() {
    }


    public ChequeServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ChequeServiceLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for BasicHttpBinding_IChequeService
    private String BasicHttpBinding_IChequeService_address = "https://services.pbn.net/TestCheque/ChequeService.svc";

    public String getBasicHttpBinding_IChequeServiceAddress() {
        return BasicHttpBinding_IChequeService_address;
    }

    // The WSDD service name defaults to the port name.
    private String BasicHttpBinding_IChequeServiceWSDDServiceName = "BasicHttpBinding_IChequeService";

    public String getBasicHttpBinding_IChequeServiceWSDDServiceName() {
        return BasicHttpBinding_IChequeServiceWSDDServiceName;
    }

    public void setBasicHttpBinding_IChequeServiceWSDDServiceName(String name) {
        BasicHttpBinding_IChequeServiceWSDDServiceName = name;
    }

    public IChequeService getBasicHttpBinding_IChequeService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(BasicHttpBinding_IChequeService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBasicHttpBinding_IChequeService(endpoint);
    }

    public IChequeService getBasicHttpBinding_IChequeService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            BasicHttpBinding_IChequeServiceStub _stub = new BasicHttpBinding_IChequeServiceStub(portAddress, this);
            _stub.setPortName(getBasicHttpBinding_IChequeServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public IChequeService getBasicHttpBinding_IChequeService(java.net.URL portAddress, String username, String password, int timeout) throws javax.xml.rpc.ServiceException {
        try {
            BasicHttpBinding_IChequeServiceStub _stub = new BasicHttpBinding_IChequeServiceStub(portAddress, this);
            _stub.setPortName(getBasicHttpBinding_IChequeServiceWSDDServiceName());

            _stub.setTimeout(timeout * 60 * 1000);
            _stub.setUsername(username);
            _stub.setPassword(password);
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBasicHttpBinding_IChequeServiceEndpointAddress(String address) {
        BasicHttpBinding_IChequeService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (IChequeService.class.isAssignableFrom(serviceEndpointInterface)) {
                BasicHttpBinding_IChequeServiceStub _stub = new BasicHttpBinding_IChequeServiceStub(new java.net.URL(BasicHttpBinding_IChequeService_address), this);
                _stub.setPortName(getBasicHttpBinding_IChequeServiceWSDDServiceName());
                return _stub;
            }
        }
        catch (Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        String inputPortName = portName.getLocalPart();
        if ("BasicHttpBinding_IChequeService".equals(inputPortName)) {
            return getBasicHttpBinding_IChequeService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "ChequeService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "BasicHttpBinding_IChequeService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {

if ("BasicHttpBinding_IChequeService".equals(portName)) {
            setBasicHttpBinding_IChequeServiceEndpointAddress(address);
        }
        else
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
