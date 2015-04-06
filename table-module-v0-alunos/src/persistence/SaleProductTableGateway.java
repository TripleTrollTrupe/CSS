package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

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
	public void addSaleProduct(int productCod, 
			double quantity) {
		// create statement
	//ID is not necessary it is generated when a Row entry is created
		Insert statement = Insert.
				into(saleProduct).
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
	
	public double totalSaleProduct(int saleProductID){
		ResultSet saleProduct= getSaleProductbySaleProductID(saleProductID);
		double qty;
		try {
			qty = saleProduct.getDouble("qty");
			double faceValue = saleProduct.getDouble("faceValue");
			return (qty*faceValue);
		} catch (SQLException e) {
			System.out.println("There is no such saleProduct");
			e.printStackTrace();
		}
		return 0;
	}
	
	public ResultSet getSaleProductList(int saleId){
		return Select.
				from(saleProduct).
				where(sp -> sp.getInt("saleID") == saleId).
				executeQuery();
	}
	
}