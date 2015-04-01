package presentation;

import persistence.Persistence;
import application.CustomerService;
import business.ApplicationException;
import business.DiscountType;

/**
 * The big bang class.
 *	
 * @author fmartins
 * @version 1.1 (4/10/2014)
 * 
 */
public class SimpleClient {

	/**
	 * Fire in the hole!!
	 * 
	 * @param args Command line parameters
	 */
	public static void main(String[] args) {
		
		// Create the persistence layer
		Persistence persistence = new persistence.Persistence();	
		
		// Creates the two available services
		CustomerService cs = new CustomerService (persistence);
//		SaleService vs = new SaleService();
			
		
		// runs a small test
		try {
			// adds a customer.
			cs.addCustomer(168027852, "Cliente 1", 217500255, DiscountType.SALE_AMOUNT);
			
			// creates a new sale
/*			int sale = vs.newSale(168027852);

			// adds two products to the database
			vs.addProductToSale(sale, 123, 10);
			vs.addProductToSale(sale, 124, 5);
			
			// gets the discount amounts
			double discount = vs.getSaleDiscount(sale);
			System.out.println(discount);
*/		} catch (ApplicationException e) {
			System.out.println("Error: " + e.getMessage());
			// for debugging purposes only. Typically, in the application
			// this information can be associated with a "details" button when
			// the error message is displayed.
			if (e.getCause() != null) 
				System.out.println("Cause: ");
			e.printStackTrace();
		}
	}
}
