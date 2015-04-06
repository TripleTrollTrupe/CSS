package business;

import java.sql.ResultSet;
import java.sql.SQLException;

import persistence.DiscountTableGateway;
import persistence.Persistence;
import services.persistence.PersistenceException;


/**
 * The table module of a customer. I choose to implement it as a singleton,
 * but it could be implemented as a regular object, and used with different
 * result sets. For this simple example, however, I find this the easiest  
 * implementation to understand the basic concepts. Please check Martin
 * Fowler's enterprise pattern book for details on other plausible implementations. 
 * 
 * @author fmartins
 * @version 1.1 (5/10/2014)
 *
 */
public class Customer extends TableModule {

	public Customer (Persistence persistence) {
		super(persistence);
	}
	
	
	/**
	 * Creates a new customer given its VAT number, its designation, phone contact, and discount type.
	 * 
	 * @param vat The customer's VAT number
	 * @param designation The customer's designation
	 * @param phoneNumber The customer's phone number
	 * @param discountType The customer's discount id
	 * @throws ApplicationException When VAT number is invalid, or the customer exists, or there is
	 * an internal referential integrity error.
	 */
	public void addCustomer (int vat, String designation, 
			int phoneNumber, DiscountType discountType) throws ApplicationException {
		// Checks if it is a valid VAT number
		if (!isValidVAT (vat))
			throw new ApplicationException ("Invalid VAT number: " + vat);
						
		// Checks that there is no other customer with the same VAT number 
		if (existsCustomer(vat))
			throw new ApplicationException ("Customer with VAT number " + vat + " already exists!");
			
		// If the customer does not exists, add it to the database 
		persistence.customerTableGateway.addCustomer(vat, designation, phoneNumber, discountType);
	}

	/**
	 * @param vat The VAT number of the customer to check if it exists
	 * @throws SQLException When an internal error accessing the data occurs.
	 */
	private boolean existsCustomer(int vat) {
		try (ResultSet rs = persistence.customerTableGateway.findWithVATNumber(vat)){
			rs.next();
			return true;
		} catch (PersistenceException | SQLException e) {
			return true;
		} 
	}


	/**
	 * @param vat The VAT number of the customer to get its internal id number
	 * @return The customerId of the customer with the vat number 
	 * @throws ApplicationException When the customer is not found or when some 
	 * obscure database internal error occurs (not in the this version of the 
	 * example) 
	 */
	public int getCustomerId (int vat) throws ApplicationException {
		try (ResultSet rs = persistence.customerTableGateway.findWithVATNumber(vat)){
			if (rs.next()) 		
				return rs.getInt("idDesconto");
			else 
				throw new ApplicationException("Customer with VAT number " + vat + " does not exist.");
		} catch (PersistenceException | SQLException e) {
			throw new ApplicationException ("Internal error obtaining customer with VAT number " + vat, e);
		} 
	}


	/**
	 * @param customerId The customerId to get the discount type of
	 * @return The DiscountType associated with the customer with customerId id
	 * @throws ApplicationException When the customer with customerId is not found or when the is 
	 * some obscure internal error (not on this example version; wait until a real database comes
	 * into play).
	 */
	public DiscountType getDiscountType (int customerId) throws ApplicationException {
		ResultSet customer = persistence.customerTableGateway.find(customerId);
		
		try {
			
			DiscountType type= DiscountTableGateway.discountIdToDiscountType(customer.getInt("discountId"));
			return type;
		} catch (SQLException e) {
			System.out.println("No such client exists in the database");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Checks if a VAT number is valid.
	 * 
	 * @param vat The VAT number to checked.
	 * @return Whether the VAT number is valid. 
	 */
	private boolean isValidVAT(int vat) {
		// If the number of digits is not 9, error!
		if (vat < 100000000 || vat > 999999999)
			return false;

		// If the first number is not 1, 2, 5, 6, 8, 9, error!
		int firstDigit = vat / 100000000;
		if (firstDigit != 1 && firstDigit != 2 && 
				firstDigit != 5 && firstDigit != 6 &&
				firstDigit != 8 && firstDigit != 9)
			return false;

		// Checks the congruence modules 11.
		int sum = 0;
		int checkDigit = vat % 10;
		vat /= 10;

		for (int i = 2; i < 10 && vat != 0; i++) {
			sum += vat % 10 * i;
			vat /= 10;
		}

		int checkDigitCalc = 11 - sum % 11;
		if (checkDigitCalc == 10)
			checkDigitCalc = 0;
		return checkDigit == checkDigitCalc;
	}
}
