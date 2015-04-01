package application;

import persistence.CustomerRowDataGateway;
import persistence.RecordNotFoundException;

/**
 * Handles customer transactions. 
 * Each public method implements a transaction script.
 * 
 * @author fmartins
 * @version 1.2 (11/02/2015)
 */
public enum CustomerService {
	INSTANCE;
	
	/**
	 * Adds a new customer. It checks that there is no other customer in the database
	 * with the same VAT.
	 * 
	 * @param vat The VAT of the customer
	 * @param denomination The customer's name
	 * @param phoneNumber The customer's phone 
	 * @param discountType The type of discount applicable to the customer
	 * @throws ApplicationException When the VAT number is invalid (we check it according 
	 * to the Portuguese legislation).
	 */
	public void addCustomer (int vat, String denomination, int phoneNumber, DiscountType discountType) 
			throws ApplicationException {
		// Checks if it is a valid VAT number
		if (!isValidVAT (vat))
			throw new ApplicationException ("Invalid VAT number: " + vat);

		// Checks that there is no other customer with the same VAT number 
		// Notice that this approach needs extra control in order to be used 
		// in a concurrent scenario. For now we keep it simple!
		try {
			CustomerRowDataGateway.getCustomerByVATNumber(vat);
		} catch (RecordNotFoundException e) { 
			// If the customer does not exists, add him to the database 
			CustomerRowDataGateway newCustomer = new CustomerRowDataGateway (vat, denomination, phoneNumber, discountType);
			newCustomer.insert();
		}
	}

	
	/**
	 * Checks if a VAT number is valid.
	 * 
	 * @param vat The number to be checked.
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
