package business;

import java.sql.ResultSet;
import java.sql.SQLException;

import persistence.Persistence;
import services.persistence.inMemory.RDBMS.Select;


/**
 * A table module for the application settings.
 * See remarks in the Customer class.
 * 
 * @author fmartins
 * @version 1.1 (5/10/2014)
 *
 */
public class ApplicationSettings extends TableModule {

	public ApplicationSettings (Persistence persistence) {
		super(persistence);
	}
	
	/**
	 * @return The sale's amount threshold for being eligible for a
	 * total sale discount.
	 * @throws ApplicationException When there is no application settings data.
	 */
	public double getAmountThreshold () throws ApplicationException {
		// TODO: program me!
		ResultSet discount = persistence.discountTableGateway.getDiscountByType(DiscountType.SALE_AMOUNT);
		try {
			return discount.getDouble("amountThreshold");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * @return The discount percentage to apply for the total sale
	 * @throws ApplicationException When there is no application settings data.
	 */
	public double getAmountThresholdPercentage () throws ApplicationException {
		// TODO: program me!
		ResultSet discount = persistence.discountTableGateway.getDiscountByType(DiscountType.SALE_AMOUNT);
		try {
			return discount.getDouble("totalAmountPercentage");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * @return The discount percentage to apply to products marked as eligible.
	 * @throws ApplicationException When there is no application settings data.
	 */
	public double getEligiblePercentage () throws ApplicationException {
		// TODO: program me!
		ResultSet discount = persistence.discountTableGateway.getDiscountByType(DiscountType.ELIGIBLE_PRODUCTS);
		try {
			return discount.getDouble("eligiblePercentage");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
