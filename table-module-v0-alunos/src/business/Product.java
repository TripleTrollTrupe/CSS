package business;

import persistence.Persistence;


/**
 * A table module for products.
 * See remarks in the Customer class.
 * 
 * @author fmartins
 * @version 1.1 (5/10/2014)
 *
 */
public class Product extends TableModule {

	public Product (Persistence persistence) {
		super(persistence);
	}
	
	/**
	 * @param productCode The code of the product to get its internal id
	 * @return The internal id of the product with code productCode
	 * @throws ApplicationException When the product with code productCode is not found
	 * or when there is an internal database error (not present in the current implementation
	 * of the in memory database with use).
	 */
	public int getProductId (int productCode) throws ApplicationException {
		// TODO: program me!
		return 0;
	}

	/**
	 * Reduces by qty the number of units in stock of product with id productId.
	 * 
	 * @param productId The product id to reduce the units in stock
	 * @param qty The number of units to remove from stock of productId
	 * @throws ApplicationException When there is not enough products in stock to fulfill
	 * the request or refer to getDoubleFieldFromProduct for the other possible exceptions. 
	 */
	public void takeFromStock (int productId, double qty) throws ApplicationException {
		// TODO: program me!
	}

	public boolean isEligibleForDiscount(int productId) throws ApplicationException {
		// TODO: program me!
		return false;
	}
	
	/**
	 * @param productId The id of the product to get its stock quantity
	 * @return The quantity in stock of the product with id productId
	 * @throws ApplicationException Refer to getDoubleFieldFromProduct.
	 */
	public double getQty(int productId) throws ApplicationException {
		// TODO: program me!
		return 0;
	}

	/**
	 * @param productId The id of the product to get its face value
	 * @return The face value of the product with id productId
	 * @throws ApplicationException Refer to getDoubleFieldFromProduct.
	 */
	public double getFaceValue(int productId) throws ApplicationException {
		// TODO: program me!
		return 0;
	}
}
