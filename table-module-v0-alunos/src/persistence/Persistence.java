package persistence;


public class Persistence {

	private Database database;
	public final ConfigurationTableGateway configurationTableGateway;
	public final CustomerTableGateway customerTableGateway;
	public final ProductTableDataGateway productTableGateway;

	public Persistence() {
		database = new Database();
		configurationTableGateway = new ConfigurationTableGateway (database.configurations);
		customerTableGateway = new CustomerTableGateway (database.customers);
		productTableGateway = new ProductTableDataGateway(database.products);
	}
	
}
