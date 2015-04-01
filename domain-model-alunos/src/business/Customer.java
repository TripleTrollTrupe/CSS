package business;

/**
 * A customer
 *	
 * @author fmartins
 * @version 1.1 (25/02/2015)
 * 
 * Remarks:
 * 1. In this iteration of the application, some of the attributes are not
 * yet used. Also, I only include the relevant getters and setters. The message is:
 * do not include getters and setters just because!!
 * 
 * 2. I included the isValidVAT method in this class, but as a static method.
 * It is a "service" method and could be included in a class that provides these
 * kind of services. To keep it simple, it is included in this class.
 * 
 * 3. Notice the precondition in the constructor: only valid vat numbers are
 * accepted. Because it is required such a validation, we opted for making available
 * the isValidVAT as public static. 
 *
 */
public class Customer {

	// Customer attributes 

	/**
	 * Customer's VAT number
	 */
	@SuppressWarnings("unused") private int vatNumber;

	/**
	 * Customer's name. In case of a company, the represents its commercial denomination 
	 */
	@SuppressWarnings("unused") private String designation;

	/**
	 * Customer's contact number
	 */
	@SuppressWarnings("unused") private int phoneNumber;

	/**
	 * Customer's discount. This discount is applied to eligible products.
	 */
	@SuppressWarnings("unused") private Discount discount;
	

	// 1. constructor 

	/**
	 * Creates a new customer given its VAT number, its designation, phone contact, and discount type.
	 * 
	 * @param vatNumber The customer's VAT number
	 * @param designation The customer's designation
	 * @param phoneNumber The customer's phone number
	 * @param discountType The customer's discount type
	 * @pre isValidVAT(vat) 
	 */
	public Customer(int vatNumber, String designation, int phoneNumber, Discount discountType) {
		this.vatNumber = vatNumber;
		this.designation = designation;
		this.phoneNumber = phoneNumber;
		this.discount = discountType;
	}

	
	// 2. getters and setters


	/**
	 * Checks if a VAT number is valid.
	 * 
	 * @param vat The number to be checked.
	 * @return Whether the VAT number is valid. 
	 */
	public static boolean isValidVAT(int vat) {
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
