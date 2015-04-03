package persistence;

import java.util.HashMap;
import java.util.Map;

 

/**
 * Represents the sale of a product
 *
 */
public class SaleProductRowDataGateway {

	private static Map<Integer, SaleProductRowDataGateway> saleProductByID = new HashMap<> ();

	private int saleProductID;

	private int nextID = 1;

	private ProductRowDataGateway product;

	private double quantity;

	public SaleProductRowDataGateway() {

	}

	public SaleProductRowDataGateway(ProductRowDataGateway product,
			double quantity) {
		this.saleProductID = nextID++;
		this.product = product;
		this.quantity = quantity;
		product.setQty(product.getQty() - quantity);
	}

	// Getters and setters
	public void setQuantity(double qty) {
		this.quantity = qty;
	}

	public void addToSale(ProductRowDataGateway prod, double qty) {
		this.product = prod;
		setQuantity(qty);
		prod.setQty(prod.getQty() - qty);
	}

	public void addToSale(ProductRowDataGateway prod) {
		this.product = prod;
		setQuantity(1);
	}

	public static SaleProductRowDataGateway getSaleProductByID(int saleProductID)
			throws RecordNotFoundException {
		SaleProductRowDataGateway saleProduct = saleProductByID
				.get(saleProductID);
		return saleProduct;
	}

	public void setProductByCode(int prodCode) throws RecordNotFoundException {
		this.product = ProductRowDataGateway.getProductByCode(prodCode);
	}

	public double getSaleProductTotal() {
		if (!this.product.isEligibleForDiscount())
			return this.quantity * this.product.getFaceValue();
		else
			return this.quantity * this.product.getFaceValue(); //TODO calcular o desconto
	}
	
	public int getSaleProductID(){
		return this.saleProductID;
	}
	
	public boolean isEligibleForDiscount(){ 
		return this.product.isEligibleForDiscount();
	}
	
	public double getQuantity(){
		return quantity;
	}
	
}
