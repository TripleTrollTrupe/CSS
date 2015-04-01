package persistence;

import services.persistence.inMemory.RDBMS.Insert;
import services.persistence.inMemory.RDBMS.Table;

public class Database {
	public final Table configurations;
	public final Table customers;
	public final Table discounts;
	public final Table products;
	public final Table sales;
	public final Table saleProducts;

	public Database() {
		configurations = new Table();
		populateConfigurationTable();
		customers = new Table();
		discounts = new Table();
		populateDiscountsTable();
		products = new Table();
		populateProductsTable();
		sales = new Table();
		saleProducts = new Table();
	}

	private void populateConfigurationTable() {
		// insert the configuration record
		Insert.
			into(configurations).
			value("totalAmountPercentage", 0.1).
			value("amountThreshold", 50.0).
			value("eligiblePercentage", 0.15).
			executeUpdate();
	}

	private void populateDiscountsTable() {
		// insert the three supported application discounts
		Insert.
			into(discounts).
			value("description", "No discount").
			executeUpdate();
		Insert.
		 	into(discounts).
		 	value("description", "Discount on the total amount of the sale").
		 	executeUpdate();
		Insert.
		 	into(discounts).
		 	value("description", "Discount on eligible products").
		 	executeUpdate();
	}

	
	/**
	 * Constants for mapping eligible discount to a string
	 */
	private static final String ELIGIBLE = "E";
	private static final String NOT_ELIGIBLE = "N";
	
	private void populateProductsTable() {
		Insert.
		    into(products).
			value("prodCod", 123).
			value("description", "Prod 1").
			value("faceValue", 100.0).
			value("qty", 500.0).
			value("discountEligibility", NOT_ELIGIBLE).  
			value("unitId", 1).  // do not care with units for now
			executeUpdate();	
		Insert.
			into(products).
			value("prodCod", 124).
			value("description", "Prod 2").
			value("faceValue", 35.0).
			value("qty", 1000.0).
			value("discountEligibility", ELIGIBLE). 
			value("unitId", 1).  // do not care with units for now
			executeUpdate();
	}
}
