
package WebService.Shelves;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "ShelvesService", targetNamespace = "http://shelves.model/", wsdlLocation = "http://localhost:8080/ShelvesService/Shelves?wsdl")
public class ShelvesService
    extends Service
{

    private final static URL SHELVESSERVICE_WSDL_LOCATION;
    private final static WebServiceException SHELVESSERVICE_EXCEPTION;
    private final static QName SHELVESSERVICE_QNAME = new QName("http://shelves.model/", "ShelvesService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/ShelvesService/Shelves?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SHELVESSERVICE_WSDL_LOCATION = url;
        SHELVESSERVICE_EXCEPTION = e;
    }

    public ShelvesService() {
        super(__getWsdlLocation(), SHELVESSERVICE_QNAME);
    }

    public ShelvesService(WebServiceFeature... features) {
        super(__getWsdlLocation(), SHELVESSERVICE_QNAME, features);
    }

    public ShelvesService(URL wsdlLocation) {
        super(wsdlLocation, SHELVESSERVICE_QNAME);
    }

    public ShelvesService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SHELVESSERVICE_QNAME, features);
    }

    public ShelvesService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ShelvesService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ShelvesHandlerRemote
     */
    @WebEndpoint(name = "ShelvesPort")
    public ShelvesHandlerRemote getShelvesPort() {
        return super.getPort(new QName("http://shelves.model/", "ShelvesPort"), ShelvesHandlerRemote.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ShelvesHandlerRemote
     */
    @WebEndpoint(name = "ShelvesPort")
    public ShelvesHandlerRemote getShelvesPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://shelves.model/", "ShelvesPort"), ShelvesHandlerRemote.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SHELVESSERVICE_EXCEPTION!= null) {
            throw SHELVESSERVICE_EXCEPTION;
        }
        return SHELVESSERVICE_WSDL_LOCATION;
    }

}