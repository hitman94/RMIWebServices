/**
 * BanqueService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package banquews;

public interface BanqueService extends javax.xml.rpc.Service {
    public java.lang.String getBanqueAddress();

    public banquews.Banque getBanque() throws javax.xml.rpc.ServiceException;

    public banquews.Banque getBanque(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
