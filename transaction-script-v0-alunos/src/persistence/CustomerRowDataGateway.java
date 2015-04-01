package persistence;

import java.util.HashMap;
import java.util.Map;

import application.DiscountType;

/**
 * An in-memory representation of a row gateway for a customer.
 *	
 * @author fmartins
 * @version 1.1 (4/10/2014)
 *
 */
public class CustomerRowDataGateway {

	/**
	 * The customer repository.
	 * There are two maps because we need to access customers both by VAT number and by customer id
	 * Notice the new syntax for creating parametric objects available from Java 7.
	 * For more information look for the Diamond Operator (<>).
	 */
	private static Map<Integer, CustomerRowDataGateway> customerByVAT = new HashMap<> ();  
	private static Map<Integer, CustomerRowDataGateway> customerById = new HashMap<> ();  
	
	/**
	 * The next client id
	 */
	private static int nextId = 1;

	
	// Customer attributes 

	/**
	 * Customer's internal identification (unique, primary key, sequential)
	 */
	private int id;

	/**
	 * Customer's VAT number
	 */
	private int vat;
	
	/**
	 * Customer's name. In case of a company, the represents its commercial denomination 
	 */
	private String designation;
	
	/**
	 * Customer's contact number
	 */
	private int phoneNumber;
	
	/**
	 * Customer's discount id. This discount is applied to eligible products.
	 */
	private int discountId;
	
	/**
	 * Constants for mapping discount types with discount ids
	 */
	private static final int NO_DISCOUNT = 1;
	private static final int SALE_AMOUNT = 2;
	private static final int ELIGIBLE_PRODUCTS = 3;

	
	// 1. constructor 

	/**
	 * Creates a new customer given its VAT number, its designation, phone contact, and discount type.
	 * 
	 * @param vat The customer's VAT number
	 * @param designation The customer's designation
	 * @param phoneNumber The customer's phone number
	 * @param discountType The customer's discount type
	 */
	public CustomerRowDataGateway(int vat, String designation, int phoneNumber,
			DiscountType discountType) {
		this.id = nextId++;
		this.vat = vat;
		this.designation = designation;
		this.phoneNumber = phoneNumber;
		setDiscountType(discountType);
	}
	
	// 2. getters and setters

	/**
	 * @return The id of the customer
     *
	 * There is no need for a setter, since this is an internal 
	 * database number (a sequential the primary key) that is not 
	 * available at the application level for change.
	 */
	public int getCustomerId() {
		return id;
	}
	
	/**
	 * Comment: There is a business logic decision not to allow changes to the VAT number
	 * of a customer. We can reflect the decision in the row gateway by not providing 
	 * the setVAT method.
     *
	 * Whenever there is repeated code (specially when using transaction script), 
	 * these decisions can migrate to the row gateway and it starts being converted 
	 * to an active record style. 
	 * Nevertheless, use with care and bare in mind that business rules are enforced by 
	 * the domain logic. When the "migration" to active record starts, please move the
	 * class to the business logic package.
	 * 
	 * @return The customer's VAT number. 
	 */
	public int getVAT() {
		return vat;
	}
	
	/**
	 * @return The customer's designation.
	 */
	public String getDesignation() {
		return designation;
	}
	
	/**
	 * Updates the customer designation.
	 * 
	 * @param designation The new designation to change to.
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * @return The customer's phone number
	 */
	public int getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Updates the phone number
	 * 
	 * @param phoneNumber The new phone number
	 */
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Notice that the setter and the getter convert the integer representing
	 * the discount id to an enumerated value (of type DiscountType) that is 
	 * then used in the application's domain logic. The internal id (primary
	 * key) must not be used directly for implementing business rules.
	 * 
	 * @return The discount id associated with the customer
	 */
	public DiscountType getDiscountType() {
		return discountId == 1 ? DiscountType.NO_DISCOUNT : 
			discountId == 2 ? DiscountType.SALE_AMOUNT : DiscountType.ELIGIBLE_PRODUCTS;
	}

	/**
	 * Updates the discount id of the customer
	 * 
	 * @param discountType The new discount type
	 */
	public void setDiscountType(DiscountType discountType) {
		this.discountId = discountType == DiscountType.SALE_AMOUNT ? SALE_AMOUNT : 
			discountType == DiscountType.ELIGIBLE_PRODUCTS ? ELIGIBLE_PRODUCTS : NO_DISCOUNT;
	}


	// 3. interaction with the repository (a memory map in this simple example)

	/**
	 * Stores the information in the repository.
	 * In the present case it stores in both maps, since we need to access the
	 * data using two different keys.
	 */
	public void insert () {
		customerByVAT.put(vat, this);
		customerById.put(id, this);
	}

	/**
	 * Fetches a customer given its VAT number and returns a CustomerRowGateway 
	 * object with the data retrieved from the repository. In case the customer
	 * is not found, a RecordNotFoundException is thrown.
	 * 
	 * @param vat The VAT number of the customer to fetch from the repository
	 * @return The CustomerRowGateway corresponding to the customer with the vat number.
	 * @throws RecordNotFoundException When the customer with the vat number is not found.
	 */
	public static CustomerRowDataGateway getCustomerByVATNumber (int vat) throws RecordNotFoundException {
		CustomerRowDataGateway result = customerByVAT.get(vat);
		if (result == null)
			throw new RecordNotFoundException ("Customer with VAT number " + vat + " does not exist!");
		else
			return result;
	}
	 
	/**
	 * Fetches a customer given its internal id number and returns a CustomerRowGateway 
	 * object with the data retrieved from the repository. In case the customer
	 * is not found, a RecordNotFoundException is thrown.

	 * @param id The Id of the customer to fecth from the repository.
	 * @return The CustomerRowGateway corresponding to the customer with the id number.
	 * @throws RecordNotFoundException When the customer with the given id number is not found.
	 */
	public static CustomerRowDataGateway getCustomerById (int id) throws RecordNotFoundException {
		CustomerRowDataGateway result = customerById.get(id);
		if (result == null)
			throw new RecordNotFoundException ("Customer with Id number " + id + " does not exist!");
		else
			return result;
	}
}
