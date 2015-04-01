package business;

/**
 * A customer discount
 * 
 * @author fmartins
 * @version 1.1 (25/02/2015)
 *
 * Remarks:
 * 1. Note that you do not need an attribute discoutType to 
 * register the type of a discount. This is discriminated by the
 * subtypes themselves.
 * 
 * 2. I follow the strategy GoF pattern.
 * 
 */
public abstract class Discount {
	
	/**
	 * Discount description
	 */
	private String description;
	
	/**
	 * Creates a discount type given its description
	 * 
	 * @param description The discount description
	 */
	public Discount(String description) {
		this.description = description;
	}

	/**
	 * @return The discount description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param sale The sale to compute the discount on
	 * @return The discount amount using the current discount type
	 */
	public abstract double computeDiscount(Sale sale);
}
