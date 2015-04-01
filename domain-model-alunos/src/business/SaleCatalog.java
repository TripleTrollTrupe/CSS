package business;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * A catalog for sales
 * 
 * @author fmartins
 * @version 1.1 (3/08/2015)
 *
 */
public class SaleCatalog {

	/**
	 * The sale's repository
	 */
	private List<Sale> sales;
		
	/**
	 * Constructs an empty sale's catalog
	 */
	public SaleCatalog() {
		this.sales = new LinkedList<Sale>();
	}

	/**
	 * Creates a new sale and adds it to the repository
	 * 
	 * @param customer The customer the sales belongs to
	 * @return The newly created sale
	 */
	public Sale newSale (Customer customer) {
		Sale v = new Sale(new Date(), customer);
		sales.add(v);
		return v;
	}

}
