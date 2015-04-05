package business;

/**
 * Handles the process sale use case.
 * 
 * @author fmartins
 * @version 1.1 (8/03/2015)
 *
 */
public class ProcessSaleHandler {
	
	/**
	 * The sale's catalog
	 */
	private SaleCatalog saleCatalog;

	/**
	 * The customer's catalog
	 */
	private CustomerCatalog customerCatalog;

	/**
	 * The product's catalog
	 */
	@SuppressWarnings("unused")
	private ProductCatalog productCatalog;
	
	/**
	 * The current sale
	 */
	@SuppressWarnings("unused")
	private Sale currentSale;
	
	/**
	 * Creates a handler for the process sale use case given 
	 * the sale, customer, and product catalogs.
	 * 
	 * @param saleCatalog A sale's catalog
	 * @param customerCatalog A customer's catalog
	 * @param productCatalog A product's catalog
	 */
	public ProcessSaleHandler(SaleCatalog saleCatalog, CustomerCatalog customerCatalog, 
							ProductCatalog productCatalog) {
		this.saleCatalog = saleCatalog;
		this.customerCatalog = customerCatalog;
		this.productCatalog = productCatalog;
	}

	/**
	 * Creates a new sale
	 * 
	 * @param vatNumber The customer's VAT number for the sale
	 * @throws ApplicationException In case the customer is not in the repository
	 */
	public void newSale (int vatNumber) throws ApplicationException {
		Customer customer = customerCatalog.getCustomer(vatNumber);
		currentSale = saleCatalog.newSale(customer);
	}

	/**
	 * Adds a product to the current sale
	 * 
	 * @param prodCod The product code to be added to the sale 
	 * @param qty The quantity of the product sold
	 * @throws ApplicationException When the sale is closed, the product code
	 * is not part of the product's catalog, or when there is not enough stock
	 * to proceed with the sale
	 */
	public void addProductToSale (int prodCod, double qty) 
					throws ApplicationException { // Done by Kyiys
		// TODO: Done by Kyiys
		if(currentSale.getStatus()!=SaleStatus.OPEN)
			throw new ApplicationException("The sale is closed");
		Product prod = productCatalog.getProduct(prodCod);
		if(prod.getQty()<qty)
			throw new ApplicationException("There is not enough quantity of this product");
		SaleProduct sp = new SaleProduct(prod, qty);
		currentSale.addSaleProduct(sp);
		
	}

	/**
	 * @return The sale's discount, according to the customer discount type
	 */
	public double getSaleDiscount ()  { // Done by Kyiys
		// TODO: Done by Kyiys
		Discount disc =currentSale.getCustomer().getDiscount();
		return disc.computeDiscount(currentSale);
	}
}
