package business;

/**
 * The main application class. It implements the 
 * start up use case. 
 * 
 * @author fmartins
 * @version 1.0 (8/03/2015)
 *
 */
public class VandA {

	/**
	 * The add customer use case handler
	 */
	private AddCustomerHandler addCustomerHandler;

	/**
	 * The process sale use case handler 
	 */
	private ProcessSaleHandler processSaleHandler;
	
	/**
	 * Performs the start up use case
	 */
	public VandA() {
		CustomerCatalog catalogoClientes = new CustomerCatalog();
		this.addCustomerHandler = new AddCustomerHandler(catalogoClientes, new DiscountCatalog());
		this.processSaleHandler = new ProcessSaleHandler(new SaleCatalog(), catalogoClientes, new ProductCatalog());
	}
	
	/**
	 * @return The add customer use case handler
	 */
	public AddCustomerHandler getAddCustomerHandler () {
		return addCustomerHandler;
	}

	/**
	 * @return The process sale use case handler
	 */
	public ProcessSaleHandler getProcessSaleHandler() {
		return processSaleHandler;
	}
}
