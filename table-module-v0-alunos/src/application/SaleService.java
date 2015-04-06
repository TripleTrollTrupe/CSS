package application;

import java.sql.ResultSet;
import java.sql.SQLException;

import persistence.DiscountTableGateway;
import persistence.Persistence;
import persistence.ProductTableDataGateway;
import persistence.SaleProductTableGateway;
import persistence.SaleTableGateway;
import services.persistence.PersistenceException;
import business.ApplicationException;
import business.DiscountType;

/**
 * Handles sales' transactions. 
 * Requests are dispatched and handled by table modules,
 * following Martin Fowler's Table Module pattern. 
 * 
 * @author fmartins
 * @version 1.1 (5/10/2014)
 *	
 */
public class SaleService {
	
	private Persistence persistence;

	public SaleService(Persistence persistence) {
		this.persistence = persistence;
	}

	/**
	 * Starts a new sale for a given customer identified by its VAT number
	 * 
	 * @param vat The VAT number of the customer the sale belongs to
	 * @return The id of the new sale. This id is useful for adding products 
	 * to the sale and for computing the sale's total, discount, etc.
	 * @throws ApplicationException When the customer with the given VAT number
	 * does not exist.
	 */
	public int newSale (int vat) throws ApplicationException {
		return persistence.saleTableGateway.addSale(vat);
			
	}

	
	/**
	 * Adds a product to an open sale.
	 * 
	 * @param saleId The sale's id.
	 * @param productCode The product's code.
	 * @param qty The amount to sell.
	 * @throws ApplicationException When the product with a given product code 
	 * does not exist, when the Sale is closed, or when there is not enough quantity
	 * (qty) of the product in stock. 
	 * 
	 * @pre: qty > 0
	 */
	public void addProductToSale (int saleId, int productCode, double qty) throws ApplicationException {
		// TODO: program me! update da sale e de saleproduct
		persistence.saleTableGateway.getSaleByID(saleId);
		ResultSet size = persistence.productTableGateway.getProductByProdCod(productCode);
			try {
				if(size.getDouble("qty")>=qty){
					persistence.saleProductTableGateway.addSaleProduct(saleId, productCode, qty);
					size.updateDouble("qty", (size.getDouble("qty")-qty));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
	/**
	 * Computes the discount amount for a sale (based on the discount type of the customer).
	 * 
	 * @param saleId The sale's id
	 * @return The discount amount
	 * @throws ApplicationException When the sale id does not exist, or there is an internal
	 * integrity error, such as the discount id associated with the sale is not found, etc.
	 */
	public double getSaleDiscount (int saleId) throws ApplicationException {
		// Done by Kyiys
		ResultSet sale=persistence.saleTableGateway.getSaleByID(saleId);
		try {
			// get client
			ResultSet customer = persistence.customerTableGateway.findWithVATNumber(sale.getInt("customer"));
			// get discount associated with client
			DiscountType discount = DiscountTableGateway.discountIdToDiscountType(customer.getInt("discountId"));
			//get table with products
			if(discount == DiscountType.NO_DISCOUNT)
				return 0;
			ResultSet config = persistence.configurationTableGateway.getConfigurationSettings();
			ResultSet list = persistence.saleProductTableGateway.getSaleProductList(saleId);
			if(discount == DiscountType.SALE_AMOUNT){
				//get percentage
				double percentage = config.getDouble("eligiblePercentage");
				//get sale total
				double total =0;
				while(!list.last()){
					total += persistence.saleProductTableGateway.totalSaleProduct(list.getInt("id"));
					list.next();
				}
				total += persistence.saleProductTableGateway.totalSaleProduct(list.getInt("id"));
				
				//get discount value
				total *=percentage;
				return total;
			}
			
			if(discount == DiscountType.ELIGIBLE_PRODUCTS){
				//product of the saleProduct
				ResultSet product = persistence.productTableGateway.getProductByProdCod(list.getInt("product"));
				//get percentage
				double percentage = config.getDouble("eligiblePercentage");
				//get sale total
				double total =0;
				while(!list.last()){
					if(product.getString("discountEligibility").equals("E"))
						total += persistence.saleProductTableGateway.totalSaleProduct(list.getInt("id"));
					list.next();
					product.close();
					product = persistence.productTableGateway.getProductByProdCod(list.getInt("product"));
				}
				total += persistence.saleProductTableGateway.totalSaleProduct(list.getInt("id"));
				
				//get discount value
				total *=percentage;
				return total;
			}
			
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	
	
}
