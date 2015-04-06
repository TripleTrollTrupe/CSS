package persistence;

import java.sql.ResultSet;

import services.persistence.inMemory.RDBMS.Select;
import services.persistence.inMemory.RDBMS.Table;
import business.DiscountType;

/**
 * Table Data Gateway for the client's table
 *
 * Please read the remarks in the CustomerTableGateway class
 * 
 * @author fmartins
 * @version 1.0 (6/10/2014)
 *
 */
public class DiscountTableGateway {
	
	/**
	 * Constants for mapping discount type with a discount id
	 */
	private static final int NO_DISCOUNT = 1;
	private static final int SALE_AMOUNT = 2;
	private static final int ELIGIBLE_PRODUCTS = 3;
	
	/**
	 * The (in memory) discount's table
	 */
	private Table discounts;
	
	public DiscountTableGateway (Table discounts) {
		this.discounts = discounts;
	}
	
	/**
	 * Gets a discount type given its type
	 * 
	 * @param discountType The discount type to get from the database
	 * @return The result set of the query
	 */
	public ResultSet getDiscountByType (DiscountType discountType) {
		return Select.
				from(discounts).
				where(p -> p.getInt("id") == 
				           discountTypeToDiscountId(discountType)).
				executeQuery();
	}

	/**
	 * Gets a discount by its Id number
	 * 
	 * @param discountId The id of the discount to search for
	 * @return The result set of the query
	 */
	public ResultSet getDiscountById(int discountId) {
		return Select.
				from(discounts).
				where(p -> p.getInt("id") == discountId).
				executeQuery();
	}
	 
	
	/**
	 * @param discountType The discount type to convert to a discount id
	 * @return The discount id associated with a given discount type
	 */
	public static int discountTypeToDiscountId(DiscountType discountType) {
		return discountType == DiscountType.SALE_AMOUNT ? SALE_AMOUNT : 
			discountType == DiscountType.ELIGIBLE_PRODUCTS ? ELIGIBLE_PRODUCTS : NO_DISCOUNT;
	}

	/**
	 * @param discountId The discountId to convert to DiscountType
	 * @return The DiscountType that corresponds to the discountId.
	 * An internal database key, as is discountId, should never be part of the domain logic.
	 * Its only purpose it to relate entities in the database. That is why the Gateway provides
	 * this static method.
	 */
	public static DiscountType discountIdToDiscountType(int discountId) {
		return discountId == SALE_AMOUNT ? DiscountType.SALE_AMOUNT :
			discountId == ELIGIBLE_PRODUCTS ? DiscountType.ELIGIBLE_PRODUCTS : DiscountType.NO_DISCOUNT;
	}
	

}
