package business;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * A sale
 *	
 * @author fmartins
 * @version 1.1 (25/02/2015)
 * 
 */
public class Sale {

	/**
	 * The date the sale was made 
	 * I'm suppressing the warning because the data attribute is not yet
	 * being used (in this version of the application)
	 */
	@SuppressWarnings("unused") private Date date;
		
	/**
	 * Whether the sale is open or closed. See constants below.
	 */
	@SuppressWarnings("unused") private SaleStatus status;
	 
	/**
	 * The customer the sales refers to
	 */
	@SuppressWarnings("unused") private Customer customer;
	
	/**
	 * The products of the sale
	 */
	@SuppressWarnings("unused") private List<SaleProduct> saleProducts;
		
	
	// 1. constructor
	
	/**
	 * Creates a new sale given the date it occurred and the customer that
	 * made the purchase.
	 * 
	 * @param date The date that the sale occurred
	 * @param customer The customer that made the purchase
	 */
	public Sale(Date date, Customer customer) {
		this.date = date;
		this.customer = customer;
		this.status = SaleStatus.OPEN;
		this.saleProducts = new LinkedList<SaleProduct>();
	}

	
	// 2. getters and setters

}
