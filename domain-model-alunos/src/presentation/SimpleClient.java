package presentation;

import business.AddCustomerHandler;
import business.ApplicationException;
import business.ProcessSaleHandler;

/**
 * A simple application client that uses both application handlers.
 *	
 * @author fmartins
 * @version 1.1 (05/03/2015)
 */
public class SimpleClient {

	private AddCustomerHandler addCustomerHandler;
	private ProcessSaleHandler processSaleHandler;
	
	public SimpleClient(AddCustomerHandler addCustomerHandler, 
			ProcessSaleHandler processSaleHandler) {
		this.addCustomerHandler = addCustomerHandler;
		this.processSaleHandler = processSaleHandler;
	}
	
	/**
	 * A simple interaction with the application services
	 */
	public void createASale() {
		
		// the interaction
		try {
			// adds a customer.
			addCustomerHandler.addCustomer(168027852, "Customer 1", 217500255, 1);

			// starts a new sale
			processSaleHandler.newSale(168027852);

			// adds two products to the sale
			processSaleHandler.addProductToSale(123, 10);
			processSaleHandler.addProductToSale(124, 5);
			
			// gets the discount amount
			System.out.println(processSaleHandler.getSaleDiscount());
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
