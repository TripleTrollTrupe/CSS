package business;

/**
 * A discount based on the amount of sale being greater than a threshold
 * 
 * @author fmartins
 * @version 1.1 (8/03/2015)
 *
 */
public class ThresholdPercentageDiscount extends Discount {

	/**
	 * The amount threshold. If the total sale is below this threshold, then
	 * the no discount is applied. Otherwise, the discount percentage is
	 * applied to the total sale. 
	 */
	@SuppressWarnings("unused") private double threshold;
	
	/**
	 * The discount percentage 
	 */
	@SuppressWarnings("unused") private double percentage;

	/**
	 * Creates a discount that will apply a percentage over the total amount
	 * of products in case this amount is above a threshold.
	 * 
	 * @param description The discount description
	 * @param threshold The amount threshold
	 * @param percentage The percentage to be applied
	 */
	public ThresholdPercentageDiscount(String description, double threshold, double percentage) {
		super (description);
		this.threshold = threshold;
		this.percentage = percentage;
	}

	@Override
	public double computeDiscount(Sale sale) {
		// TODO: program me!
		return 0;
	}
	
}
