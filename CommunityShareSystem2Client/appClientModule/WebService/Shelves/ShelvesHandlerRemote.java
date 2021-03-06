
package WebService.Shelves;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ShelvesHandlerRemote", targetNamespace = "http://shelves.model/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ShelvesHandlerRemote {


    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "addNormalShelf", targetNamespace = "http://shelves.model/", className = "WebService.Shelves.AddNormalShelf")
    @ResponseWrapper(localName = "addNormalShelfResponse", targetNamespace = "http://shelves.model/", className = "WebService.Shelves.AddNormalShelfResponse")
    @Action(input = "http://shelves.model/ShelvesHandlerRemote/addNormalShelfRequest", output = "http://shelves.model/ShelvesHandlerRemote/addNormalShelfResponse")
    public boolean addNormalShelf(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "addOrRenewRental", targetNamespace = "http://shelves.model/", className = "WebService.Shelves.AddOrRenewRental")
    @ResponseWrapper(localName = "addOrRenewRentalResponse", targetNamespace = "http://shelves.model/", className = "WebService.Shelves.AddOrRenewRentalResponse")
    @Action(input = "http://shelves.model/ShelvesHandlerRemote/addOrRenewRentalRequest", output = "http://shelves.model/ShelvesHandlerRemote/addOrRenewRentalResponse")
    public boolean addOrRenewRental(
        @WebParam(name = "arg0", targetNamespace = "")
        EMedium arg0);

    /**
     * 
     * @return
     *     returns java.util.List<java.lang.String>
     * @throws Exception_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getShelves", targetNamespace = "http://shelves.model/", className = "WebService.Shelves.GetShelves")
    @ResponseWrapper(localName = "getShelvesResponse", targetNamespace = "http://shelves.model/", className = "WebService.Shelves.GetShelvesResponse")
    @Action(input = "http://shelves.model/ShelvesHandlerRemote/getShelvesRequest", output = "http://shelves.model/ShelvesHandlerRemote/getShelvesResponse", fault = {
        @FaultAction(className = Exception_Exception.class, value = "http://shelves.model/ShelvesHandlerRemote/getShelves/Fault/Exception")
    })
    public List<String> getShelves()
        throws Exception_Exception
    ;

    /**
     * 
     * @param arg1
     * @param arg0
     * @throws Exception_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "addRental", targetNamespace = "http://shelves.model/", className = "WebService.Shelves.AddRental")
    @ResponseWrapper(localName = "addRentalResponse", targetNamespace = "http://shelves.model/", className = "WebService.Shelves.AddRentalResponse")
    @Action(input = "http://shelves.model/ShelvesHandlerRemote/addRentalRequest", output = "http://shelves.model/ShelvesHandlerRemote/addRentalResponse", fault = {
        @FaultAction(className = Exception_Exception.class, value = "http://shelves.model/ShelvesHandlerRemote/addRental/Fault/Exception")
    })
    public void addRental(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        EMedium arg1)
        throws Exception_Exception
    ;

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     * @throws Exception_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "removeRental", targetNamespace = "http://shelves.model/", className = "WebService.Shelves.RemoveRental")
    @ResponseWrapper(localName = "removeRentalResponse", targetNamespace = "http://shelves.model/", className = "WebService.Shelves.RemoveRentalResponse")
    @Action(input = "http://shelves.model/ShelvesHandlerRemote/removeRentalRequest", output = "http://shelves.model/ShelvesHandlerRemote/removeRentalResponse", fault = {
        @FaultAction(className = Exception_Exception.class, value = "http://shelves.model/ShelvesHandlerRemote/removeRental/Fault/Exception")
    })
    public boolean removeRental(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        EMedium arg1)
        throws Exception_Exception
    ;

    /**
     * 
     * @return
     *     returns java.util.List<WebService.Shelves.EMedium>
     * @throws Exception_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getRentals", targetNamespace = "http://shelves.model/", className = "WebService.Shelves.GetRentals")
    @ResponseWrapper(localName = "getRentalsResponse", targetNamespace = "http://shelves.model/", className = "WebService.Shelves.GetRentalsResponse")
    @Action(input = "http://shelves.model/ShelvesHandlerRemote/getRentalsRequest", output = "http://shelves.model/ShelvesHandlerRemote/getRentalsResponse", fault = {
        @FaultAction(className = Exception_Exception.class, value = "http://shelves.model/ShelvesHandlerRemote/getRentals/Fault/Exception")
    })
    public List<EMedium> getRentals()
        throws Exception_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<WebService.Shelves.EMedium>
     * @throws Exception_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getShelfRentals", targetNamespace = "http://shelves.model/", className = "WebService.Shelves.GetShelfRentals")
    @ResponseWrapper(localName = "getShelfRentalsResponse", targetNamespace = "http://shelves.model/", className = "WebService.Shelves.GetShelfRentalsResponse")
    @Action(input = "http://shelves.model/ShelvesHandlerRemote/getShelfRentalsRequest", output = "http://shelves.model/ShelvesHandlerRemote/getShelfRentalsResponse", fault = {
        @FaultAction(className = Exception_Exception.class, value = "http://shelves.model/ShelvesHandlerRemote/getShelfRentals/Fault/Exception")
    })
    public List<EMedium> getShelfRentals(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0)
        throws Exception_Exception
    ;

    /**
     * 
     * @param arg0
     * @throws Exception_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "removeShelf", targetNamespace = "http://shelves.model/", className = "WebService.Shelves.RemoveShelf")
    @ResponseWrapper(localName = "removeShelfResponse", targetNamespace = "http://shelves.model/", className = "WebService.Shelves.RemoveShelfResponse")
    @Action(input = "http://shelves.model/ShelvesHandlerRemote/removeShelfRequest", output = "http://shelves.model/ShelvesHandlerRemote/removeShelfResponse", fault = {
        @FaultAction(className = Exception_Exception.class, value = "http://shelves.model/ShelvesHandlerRemote/removeShelf/Fault/Exception")
    })
    public void removeShelf(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0)
        throws Exception_Exception
    ;

}
