package presentation;

import application.ApplicationException;
import application.CustomerService;
import application.DiscountType;
import application.SaleService;

/**
 * A simple application client that uses both services.
 *	
 * @author fmartins
 * @version 1.2 (11/02/2015)
 * 
 */
public class SimpleClient {

	/**
	 * A simple interaction with the application services
	 * 
	 * @param args Command line parameters
	 */
	public static void main(String[] args) {

		// Access both available services
		CustomerService cs = CustomerService.INSTANCE;
		SaleService vs = SaleService.INSTANCE;		
		
		// the interaction
		try {
			// adds a customer.
			cs.addCustomer(168027852, "Customer 1", 217500255, DiscountType.SALE_AMOUNT);

			// creates a new sale
			int sale = vs.newSale(168027852);

			// adds two products to the database
			vs.addProductToSale(sale, 123, 10);
			vs.addProductToSale(sale, 124, 5);
			
			// gets the discount amounts
			double discount = vs.getSaleDiscount(sale);
			System.out.println(discount);
		} catch (ApplicationException e) {
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
