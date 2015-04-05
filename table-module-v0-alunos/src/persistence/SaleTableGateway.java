package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import services.persistence.inMemory.RDBMS.Insert;
import services.persistence.inMemory.RDBMS.Select;
import services.persistence.inMemory.RDBMS.Table;

public class SaleTableGateway {

	private Table sales;
	
	public SaleTableGateway(Table sales){
		this.sales=sales;		
	}
	
	public ResultSet getSaleByID(int saleID){
		return Select.
				from(sales).
				where(s -> s.getInt("saleID")==saleID).
				executeQuery();
	}
	
	public int addSale(int customerVAT){
		int result = 0;
		Insert statement = Insert.
				into(sales).
				value("customer", customerVAT);  //TODO not really sure how to do it here
		// execute statement
		statement.executeUpdate();
		//TODO return Sale ID think it's done
		ResultSet rs = Select.
				from(sales).
				where(s -> s.getInt("customer") == customerVAT).
				executeQuery();
		try {
			rs.last(); // the last row is the one with the biggest saleID?
			result = rs.getInt("saleID");
		} catch (SQLException e) {
		}
		return result;
	}
	
	public void updateSale(int saleId, int productCode, double qty){
			updateSaleProduct(productCode,qty);
		//TODO still working on this
		// There is no point in updating the sales when adding a product to the sale
				
	}
	
}
