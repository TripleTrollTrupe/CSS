package business;

import java.util.List;



/**
 * A discount based on the products marked as eligible for discount
 * 
 * @author fmartins
 * @version 1.1 (25/02/2015)
 *
 */
public class EligibleProductsDiscount extends Discount {

	/**
	 * The discount percentage
	 */
	@SuppressWarnings("unused") private double percentage;

	/**
	 * Creates a discount that will apply a percentage over the total amount
	 * of products marked as eligible for discount.
	 * 
	 * @param description The discount description
	 * @param percentage The percentage to be applied
	 */
	public EligibleProductsDiscount(String description, double percentage) {
		super (description);
		this.percentage = percentage;
	}

	@Override
	public double computeDiscount(Sale sale) { // Kyiys was here
		Product prod;
		double disc = 0.0;
		// TODO: Done by Cocheta			
		List<SaleProduct> list=sale.getSaleProducts();
		for(SaleProduct sp : list){
			prod= sp.getProduct();
			if(prod.isDiscountEligibility())
				disc += (prod.getFaceValue()*prod.getQty())*(percentage/100);
		}
		return disc;

	}
	
}
