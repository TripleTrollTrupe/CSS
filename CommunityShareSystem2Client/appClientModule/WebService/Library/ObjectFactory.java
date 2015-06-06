
package WebService.Library;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the WebService.Library package. 
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

    private final static QName _GetAllLendables_QNAME = new QName("http://lendables.model/", "getAllLendables");
    private final static QName _GetLendableResponse_QNAME = new QName("http://lendables.model/", "getLendableResponse");
    private final static QName _GetAllLendablesResponse_QNAME = new QName("http://lendables.model/", "getAllLendablesResponse");
    private final static QName _GetLendable_QNAME = new QName("http://lendables.model/", "getLendable");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: WebService.Library
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAllLendables }
     * 
     */
    public GetAllLendables createGetAllLendables() {
        return new GetAllLendables();
    }

    /**
     * Create an instance of {@link GetLendableResponse }
     * 
     */
    public GetLendableResponse createGetLendableResponse() {
        return new GetLendableResponse();
    }

    /**
     * Create an instance of {@link GetAllLendablesResponse }
     * 
     */
    public GetAllLendablesResponse createGetAllLendablesResponse() {
        return new GetAllLendablesResponse();
    }

    /**
     * Create an instance of {@link GetLendable }
     * 
     */
    public GetLendable createGetLendable() {
        return new GetLendable();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllLendables }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://lendables.model/", name = "getAllLendables")
    public JAXBElement<GetAllLendables> createGetAllLendables(GetAllLendables value) {
        return new JAXBElement<GetAllLendables>(_GetAllLendables_QNAME, GetAllLendables.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLendableResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://lendables.model/", name = "getLendableResponse")
    public JAXBElement<GetLendableResponse> createGetLendableResponse(GetLendableResponse value) {
        return new JAXBElement<GetLendableResponse>(_GetLendableResponse_QNAME, GetLendableResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllLendablesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://lendables.model/", name = "getAllLendablesResponse")
    public JAXBElement<GetAllLendablesResponse> createGetAllLendablesResponse(GetAllLendablesResponse value) {
        return new JAXBElement<GetAllLendablesResponse>(_GetAllLendablesResponse_QNAME, GetAllLendablesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLendable }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://lendables.model/", name = "getLendable")
    public JAXBElement<GetLendable> createGetLendable(GetLendable value) {
        return new JAXBElement<GetLendable>(_GetLendable_QNAME, GetLendable.class, null, value);
    }

}
