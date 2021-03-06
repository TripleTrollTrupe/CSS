package business;


/**
 * A product 
 * 
 * @author fmartins
 * @version 1.1 (25/02/2015)
 *
 * Remarks:
 * 1. In this iteration of the application, some of the attributes are not
 * yet used. Also, I only include the relevant getters and setters. The message is:
 * do not include getters and setters just because!!
 * 
 */
public class Product {

	// Product attributes
	
	/**
	 * The product code. This code is the one present in the product that customers have access to.
	 */	
	@SuppressWarnings("unused") private int prodCod;

	/**
	 * Product's description 
	 */
	@SuppressWarnings("unused") private String description;

	/**
	 * Product's face value
	 */
	@SuppressWarnings("unused") private double faceValue;

	/**
	 * Product's stock quantity
	 */
	@SuppressWarnings("unused") private double qty;

	/**
	 * If the product is eligible for a discount
	 */
	@SuppressWarnings("unused") private boolean discountEligibility;

	/**
	 * Product's units
	 */
	@SuppressWarnings("unused") private Unit unit;

	
	// 1. constructor 
	
	/**
	 * Creates a new product given its code, description, face value, 
	 * stock quantity, if it is eligible for discount, and its units.
	 * 
	 * @param prodCod The product code
	 * @param description The product description
	 * @param faceValue The value by which the product should be sold
	 * @param qty The number of units available in stock
	 * @param discountEligibility If the product is eligible for discount
	 * @param unitId The units of the product quantity.	
	 */
	public Product(int prodCod, String description,
			double faceValue, double qty, boolean discountEligibility,
			Unit unit) {
		this.prodCod = prodCod;
		this.description = description;
		this.faceValue = faceValue;
		this.qty = qty;
		this.discountEligibility = discountEligibility;
		this.unit = unit;
	}

	// 2. getters and setters - Kaze was here
	
	public int getProdCod() {
		return prodCod;
	}


	public void setProdCod(int prodCod) {
		this.prodCod = prodCod;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public double getFaceValue() {
		return faceValue;
	}


	public void setFaceValue(double faceValue) {
		this.faceValue = faceValue;
	}


	public double getQty() {
		return qty;
	}


	public void setQty(double qty) {
		this.qty = qty;
	}


	public boolean isDiscountEligibility() {
		return discountEligibility;
	}


	public void setDiscountEligibility(boolean discountEligibility) {
		this.discountEligibility = discountEligibility;
	}


	public Unit getUnit() {
		return unit;
	}


	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	
	
	
	
}
