
package WebService.Shelves;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the WebService.Shelves package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _RemoveRentalResponse_QNAME = new QName("http://shelves.model/", "removeRentalResponse");
    private final static QName _AddOrRenewRental_QNAME = new QName("http://shelves.model/", "addOrRenewRental");
    private final static QName _RemoveRental_QNAME = new QName("http://shelves.model/", "removeRental");
    private final static QName _RemoveShelfResponse_QNAME = new QName("http://shelves.model/", "removeShelfResponse");
    private final static QName _GetShelvesResponse_QNAME = new QName("http://shelves.model/", "getShelvesResponse");
    private final static QName _AddOrRenewRentalResponse_QNAME = new QName("http://shelves.model/", "addOrRenewRentalResponse");
    private final static QName _AddNormalShelfResponse_QNAME = new QName("http://shelves.model/", "addNormalShelfResponse");
    private final static QName _RemoveShelf_QNAME = new QName("http://shelves.model/", "removeShelf");
    private final static QName _GetRentals_QNAME = new QName("http://shelves.model/", "getRentals");
    private final static QName _GetShelfRentalsResponse_QNAME = new QName("http://shelves.model/", "getShelfRentalsResponse");
    private final static QName _Exception_QNAME = new QName("http://shelves.model/", "Exception");
    private final static QName _GetShelfRentals_QNAME = new QName("http://shelves.model/", "getShelfRentals");
    private final static QName _GetRentalsResponse_QNAME = new QName("http://shelves.model/", "getRentalsResponse");
    private final static QName _AddRental_QNAME = new QName("http://shelves.model/", "addRental");
    private final static QName _GetShelves_QNAME = new QName("http://shelves.model/", "getShelves");
    private final static QName _AddRentalResponse_QNAME = new QName("http://shelves.model/", "addRentalResponse");
    private final static QName _AddNormalShelf_QNAME = new QName("http://shelves.model/", "addNormalShelf");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: WebService.Shelves
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddNormalShelf }
     * 
     */
    public AddNormalShelf createAddNormalShelf() {
        return new AddNormalShelf();
    }

    /**
     * Create an instance of {@link AddRentalResponse }
     * 
     */
    public AddRentalResponse createAddRentalResponse() {
        return new AddRentalResponse();
    }

    /**
     * Create an instance of {@link AddRental }
     * 
     */
    public AddRental createAddRental() {
        return new AddRental();
    }

    /**
     * Create an instance of {@link GetShelves }
     * 
     */
    public GetShelves createGetShelves() {
        return new GetShelves();
    }

    /**
     * Create an instance of {@link GetRentals }
     * 
     */
    public GetRentals createGetRentals() {
        return new GetRentals();
    }

    /**
     * Create an instance of {@link GetShelfRentalsResponse }
     * 
     */
    public GetShelfRentalsResponse createGetShelfRentalsResponse() {
        return new GetShelfRentalsResponse();
    }

    /**
     * Create an instance of {@link GetRentalsResponse }
     * 
     */
    public GetRentalsResponse createGetRentalsResponse() {
        return new GetRentalsResponse();
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link GetShelfRentals }
     * 
     */
    public GetShelfRentals createGetShelfRentals() {
        return new GetShelfRentals();
    }

    /**
     * Create an instance of {@link GetShelvesResponse }
     * 
     */
    public GetShelvesResponse createGetShelvesResponse() {
        return new GetShelvesResponse();
    }

    /**
     * Create an instance of {@link RemoveShelfResponse }
     * 
     */
    public RemoveShelfResponse createRemoveShelfResponse() {
        return new RemoveShelfResponse();
    }

    /**
     * Create an instance of {@link RemoveShelf }
     * 
     */
    public RemoveShelf createRemoveShelf() {
        return new RemoveShelf();
    }

    /**
     * Create an instance of {@link AddNormalShelfResponse }
     * 
     */
    public AddNormalShelfResponse createAddNormalShelfResponse() {
        return new AddNormalShelfResponse();
    }

    /**
     * Create an instance of {@link AddOrRenewRentalResponse }
     * 
     */
    public AddOrRenewRentalResponse createAddOrRenewRentalResponse() {
        return new AddOrRenewRentalResponse();
    }

    /**
     * Create an instance of {@link AddOrRenewRental }
     * 
     */
    public AddOrRenewRental createAddOrRenewRental() {
        return new AddOrRenewRental();
    }

    /**
     * Create an instance of {@link RemoveRentalResponse }
     * 
     */
    public RemoveRentalResponse createRemoveRentalResponse() {
        return new RemoveRentalResponse();
    }

    /**
     * Create an instance of {@link RemoveRental }
     * 
     */
    public RemoveRental createRemoveRental() {
        return new RemoveRental();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveRentalResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shelves.model/", name = "removeRentalResponse")
    public JAXBElement<RemoveRentalResponse> createRemoveRentalResponse(RemoveRentalResponse value) {
        return new JAXBElement<RemoveRentalResponse>(_RemoveRentalResponse_QNAME, RemoveRentalResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddOrRenewRental }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shelves.model/", name = "addOrRenewRental")
    public JAXBElement<AddOrRenewRental> createAddOrRenewRental(AddOrRenewRental value) {
        return new JAXBElement<AddOrRenewRental>(_AddOrRenewRental_QNAME, AddOrRenewRental.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveRental }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shelves.model/", name = "removeRental")
    public JAXBElement<RemoveRental> createRemoveRental(RemoveRental value) {
        return new JAXBElement<RemoveRental>(_RemoveRental_QNAME, RemoveRental.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveShelfResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shelves.model/", name = "removeShelfResponse")
    public JAXBElement<RemoveShelfResponse> createRemoveShelfResponse(RemoveShelfResponse value) {
        return new JAXBElement<RemoveShelfResponse>(_RemoveShelfResponse_QNAME, RemoveShelfResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetShelvesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shelves.model/", name = "getShelvesResponse")
    public JAXBElement<GetShelvesResponse> createGetShelvesResponse(GetShelvesResponse value) {
        return new JAXBElement<GetShelvesResponse>(_GetShelvesResponse_QNAME, GetShelvesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddOrRenewRentalResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shelves.model/", name = "addOrRenewRentalResponse")
    public JAXBElement<AddOrRenewRentalResponse> createAddOrRenewRentalResponse(AddOrRenewRentalResponse value) {
        return new JAXBElement<AddOrRenewRentalResponse>(_AddOrRenewRentalResponse_QNAME, AddOrRenewRentalResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNormalShelfResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shelves.model/", name = "addNormalShelfResponse")
    public JAXBElement<AddNormalShelfResponse> createAddNormalShelfResponse(AddNormalShelfResponse value) {
        return new JAXBElement<AddNormalShelfResponse>(_AddNormalShelfResponse_QNAME, AddNormalShelfResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveShelf }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shelves.model/", name = "removeShelf")
    public JAXBElement<RemoveShelf> createRemoveShelf(RemoveShelf value) {
        return new JAXBElement<RemoveShelf>(_RemoveShelf_QNAME, RemoveShelf.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRentals }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shelves.model/", name = "getRentals")
    public JAXBElement<GetRentals> createGetRentals(GetRentals value) {
        return new JAXBElement<GetRentals>(_GetRentals_QNAME, GetRentals.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetShelfRentalsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shelves.model/", name = "getShelfRentalsResponse")
    public JAXBElement<GetShelfRentalsResponse> createGetShelfRentalsResponse(GetShelfRentalsResponse value) {
        return new JAXBElement<GetShelfRentalsResponse>(_GetShelfRentalsResponse_QNAME, GetShelfRentalsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shelves.model/", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetShelfRentals }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shelves.model/", name = "getShelfRentals")
    public JAXBElement<GetShelfRentals> createGetShelfRentals(GetShelfRentals value) {
        return new JAXBElement<GetShelfRentals>(_GetShelfRentals_QNAME, GetShelfRentals.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRentalsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shelves.model/", name = "getRentalsResponse")
    public JAXBElement<GetRentalsResponse> createGetRentalsResponse(GetRentalsResponse value) {
        return new JAXBElement<GetRentalsResponse>(_GetRentalsResponse_QNAME, GetRentalsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddRental }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shelves.model/", name = "addRental")
    public JAXBElement<AddRental> createAddRental(AddRental value) {
        return new JAXBElement<AddRental>(_AddRental_QNAME, AddRental.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetShelves }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shelves.model/", name = "getShelves")
    public JAXBElement<GetShelves> createGetShelves(GetShelves value) {
        return new JAXBElement<GetShelves>(_GetShelves_QNAME, GetShelves.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddRentalResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shelves.model/", name = "addRentalResponse")
    public JAXBElement<AddRentalResponse> createAddRentalResponse(AddRentalResponse value) {
        return new JAXBElement<AddRentalResponse>(_AddRentalResponse_QNAME, AddRentalResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNormalShelf }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shelves.model/", name = "addNormalShelf")
    public JAXBElement<AddNormalShelf> createAddNormalShelf(AddNormalShelf value) {
        return new JAXBElement<AddNormalShelf>(_AddNormalShelf_QNAME, AddNormalShelf.class, null, value);
    }

}
