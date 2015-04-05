package persistence;


public class Persistence {

	private Database database;
	public final ConfigurationTableGateway configurationTableGateway;
	public final CustomerTableGateway customerTableGateway;
	public final ProductTableDataGateway productTableGateway;
	public final SaleTableGateway saleTableGateway;
	public final SaleProductTableGateway saleProductTableGateway;
	public final DiscountTableGateway discountTableGateway; 

	public Persistence() {
		database = new Database();
		configurationTableGateway = new ConfigurationTableGateway (database.configurations);
		customerTableGateway = new CustomerTableGateway (database.customers);
		productTableGateway = new ProductTableDataGateway(database.products);
		saleTableGateway = new SaleTableGateway(database.sales);
		saleProductTableGateway = new SaleProductTableGateway(database.saleProducts);
		discountTableGateway = new DiscountTableGateway(database.discounts);
	}
	
}
