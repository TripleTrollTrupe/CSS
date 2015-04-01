package persistence;

import java.sql.ResultSet;

import services.persistence.inMemory.RDBMS.Select;
import services.persistence.inMemory.RDBMS.Table;
import services.persistence.inMemory.RDBMS.Update;

/**
 * Table Data Gateway for the product's table
 * 
 * Please read the remarks in the CustomerTableGateway class
 *  
 * @author fmartins
 * @version 1.1 (5/10/2014)
 *
 */
public class ProductTableDataGateway {
	
	/**
	 * The (in memory) products' table
	 */
	private Table products;

	
	public ProductTableDataGateway (Table products) {
		this.products = products;
	}
	
	
	/**
	 * Gets a product given its product code.
	 * 
	 * @param productCod The code of the product to search for
	 * @return The result set of the query
	 */
	public ResultSet getProductByProdCod (int productCod) {
		return Select.
				from(products).
				where(p -> p.getInt("prodCod") == productCod).
				executeQuery();
	}
	
	/**
	 * Gets a product given its internal id.
	 *
	 * @param productId The internal code of the product to search for
	 * @return The result set of the query
	 */
	public ResultSet getProductById (int productId) {
		return Select.
				from(products).
				where(p -> p.getInt("id") == productId).
				executeQuery();
	}
		
	/**
	 * Updates the quantity in stock of a product id
	 * 
	 * @param productId The internal code of the product to update its quantity
	 * @param qty The new product stock quantity
	 */
	public void updateProductStockQuantity(int productId, double qty) {
		// create statement
		Update statement = Update.
				table(products).
				set(p -> p.setDouble("qty", qty)).
				where(p -> p.getInt("id") == productId);
		// execute statement
		statement.executeUpdate();
	}
}
