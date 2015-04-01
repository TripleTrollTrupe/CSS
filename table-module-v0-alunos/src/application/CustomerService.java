package application;

import javax.sql.rowset.FilteredRowSet;

import persistence.Persistence;
import services.persistence.PersistenceException;
import business.ApplicationException;
import business.Customer;
import business.DiscountType;

/**
 * Handles customer transactions. 
 * Requests are dispatched and handled by table modules.
 * Read Martin Fowler's Table Module pattern. 
 * 
 * @author fmartins
 * @version 1.1 (5/10/2014)
 */
public class CustomerService {

	private Persistence persistence;

	public CustomerService(Persistence persistence) {
		this.persistence = persistence;
	}
	
	/**
	 * Adds a new customer.
	 * 
	 * @param vat The VAT of the customer
	 * @param designation The customer's name
	 * @param phoneNumber The customer's phone 
	 * @param discountType The type of discount applicable to the customer
	 * @throws ApplicationException When the VAT number is invalid (we check according 
	 * to the Portuguese legislation), when it is already in the database, 
	 * or when is some unexpected persistence error.
	 */
	public void addCustomer (int vat, String designation, 
			int phoneNumber, DiscountType discountType) throws ApplicationException {
		try {
			FilteredRowSet rs = persistence.customerTableGateway.findWithVATNumber(vat);
			Customer customer = new Customer (persistence);
			customer.addCustomer(vat, designation, phoneNumber, discountType);
			persistence.customerTableGateway.update(rs);
		} catch (PersistenceException e) {
			throw new ApplicationException("Error adding a customer with VAT number " + vat);
		}
	}
}
