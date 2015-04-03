package application;


import persistence.CustomerRowDataGateway;
import persistence.RecordNotFoundException;
import persistence.SaleRowDataGateway;


/**
 * Handles sale's transactions. 
 * Each public method implements a transaction script.	
 *	
 * @author fmartins
 * @version 1.2 (11/02/2015)
 * 
 */
public enum SaleService {
	INSTANCE;
	
	/**
	 * Starts a new sale for a given customer identified by its VAT number
	 * 
	 * @param vat The VAT number of the customer the sale belongs to
	 * @return The id of the new sale. This id is useful for adding products 
	 * to the sale and for computing the sale's total, the total discount, etc.
	 * @throws ApplicationException When the customer does not exist.
	 * @throws RecordNotFoundException 
	 */ 
	public int newSale (int vat) throws ApplicationException {		
		//TODO - Kaze was here thinks it's done
		

		// Checks that there is no other customer with the same VAT number 
		// Notice that this approach needs extra control in order to be used 
		// in a concurrent scenario. For now we keep it simple!
		try {
			SaleRowDataGateway.getSaleByVATNumber(vat);
		} catch (RecordNotFoundException e) { 
			// If the customer does not exists, add him to the database 
			CustomerRowDataGateway newCustomer = new CustomerRowDataGateway (vat, denomination, phoneNumber, discountType);
			newCustomer.insert();
		}
		 
	}

	
	/**
	 * Adds a product to an open sale.
	 * 
	 * @param saleId The sale's id.
	 * @param productCode The product's code.
	 * @param qty The amount to sell.
	 * @throws ApplicationException When the sale is closed, the product code does not exist,
	 * there's not enough amount of product to be sold. 
	 * @throws RecordNotFoundException 
	 * 
	 * @pre: qty > 0
	 */
	public void addProductToSale (int saleId, int productCode, double qty) throws ApplicationException, RecordNotFoundException {
		//TODO = Kaze was here think's it's okay
		SaleRowDataGateway sale = SaleRowDataGateway.getSaleByID(saleId);
		sale.addProduct(productCode,qty);
	}
	
	/**
	 * Computes the discount amount for a sale (based on the discount type of the customer).
	 * 
	 * @param saleId The sale's id
	 * @return The discount amount
	 * @throws ApplicationException When the sale does not exist or when an unexpected 
	 * referential integrity problem occurs.
	 */
	public double getSaleDiscount (int saleId) throws ApplicationException {
		SaleRowDataGateway sale = SaleRowDataGateway.getSaleByID(saleId);
		return sale.getDiscountTotal();
	}
}
