package business;

import java.util.HashMap;
import java.util.Map;

/**
 * A catalog of products
 * 
 * @author fmartins
 * @version 1.1 (25/02/2015)
 *
 */
public class ProductCatalog {

	/**
	 * The product's repository
	 */
	private Map<Integer, Product> products;
	
	/**
	 * Constructs a product's catalog with the predefined products and stocks
	 */
	public ProductCatalog() {
		products = new HashMap<Integer, Product> ();
		loadProducts();
	}
	
	/**
	 * Load predefined products
	 */
	private void loadProducts() {
		products.put(123, new Product(123, "Prod 1", 100, 500, false, new Unit()));
		products.put(124, new Product(124, "Prod 2", 35, 1000, true, new Unit()));
	}

	/**
	 * Finds a product given its code
	 * 
	 * @param prodCod The code of the product to find
	 * @return The product associated with the product code
	 * @throws ApplicationException When the product with a given prodCod is not found
	 */
	public Product getProduct (int prodCod) throws ApplicationException {
		// TODO: program me!
		return null;
	}

	
}
