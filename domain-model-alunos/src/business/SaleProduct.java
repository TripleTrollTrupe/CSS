package business;


/**
 * A sale product.
 *	
 * @author fmartins
 * @version 1.1 (10/03/2015)
 *
 */
public class SaleProduct {

	// Sale product attributes 

	/**
	 * The product of the sale
	 */
	@SuppressWarnings("unused") private Product product;

	/**
	 * The number of items purchased
	 */
	@SuppressWarnings("unused") private double qty;
	

	// 1. constructors

	/**
	 * Creates a product that is part of a sale. The qty is the quantity of items in the sale. 
	 * 
	 * @param product The product to be associated with the sale.
	 * @param qty The number of products sold.
	 */
	public SaleProduct (Product product, double qty) {
		this.product = product;
		this.qty = qty;
	}
	

	// 2. getters and setters

}
