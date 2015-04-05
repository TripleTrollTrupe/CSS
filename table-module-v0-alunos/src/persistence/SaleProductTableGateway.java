package persistence;

import java.sql.ResultSet;

import services.persistence.inMemory.RDBMS.Insert;
import services.persistence.inMemory.RDBMS.Select;
import services.persistence.inMemory.RDBMS.Table;
import services.persistence.inMemory.RDBMS.Update;

public class SaleProductTableGateway {

	private Table saleProduct;
	
	public SaleProductTableGateway(Table saleProduct){
		this.saleProduct=saleProduct;
	}
	
	public ResultSet getSaleProductbySaleProductID(int saleProductID){
		return Select.
				from(saleProduct).
				where(sp -> sp.getInt("saleProductID") == saleProductID).
				executeQuery();
}
	
	//creates sale of a certain product
	public void addSaleProduct(int saleProductID, int productCod, 
			double quantity) {
		// create statement
	
		Insert statement = Insert.
				into(saleProduct).
				value("saleProductID", saleProductID).
				value("product", productCod).  //TODO not really sure how to do it here
				value("quantity", quantity);
		// execute statement
		statement.executeUpdate();
	}
	
	//Update the quantity of the sale of a certain product
	public void updateSaleProduct(int saleProductID, double quantity){
		Update statement = Update.
				table(saleProduct).
				set(p -> p.setDouble("qty", quantity)).
				where(p -> p.getInt("id") == saleProductID);
		// execute statement
		statement.executeUpdate();
	}
	

}