package startup;

import presentation.SimpleClient;
import business.VandA;

/**
 * Implements the startup use case. It creates the application, 
 * a simple client that interacts with the application, passing
 * it the application handlers. 
 * 
 * @author fmartins
 * @version 1.0 (08/03/2015)
 *
 */
public class Startup {

	public static void main(String[] args) {
		// Creates the application main object
		VandA app = new VandA ();
		
		// Creates the simple interaction client and 
		// passes it the application handlers
		SimpleClient simpleClient = new SimpleClient(app.getAddCustomerHandler(), 
				app.getProcessSaleHandler());
		simpleClient.createASale();
	}

}
