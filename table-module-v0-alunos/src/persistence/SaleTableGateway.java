package persistence;

import java.sql.ResultSet;

import services.persistence.inMemory.RDBMS.Insert;
import services.persistence.inMemory.RDBMS.Select;
import services.persistence.inMemory.RDBMS.Table;
import services.persistence.inMemory.RDBMS.Update;

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
		Insert statement = Insert.
				into(sales).
				value("customer", customerVAT);  //TODO not really sure how to do it here
		// execute statement
		statement.executeUpdate();
		//TODO return Sale ID
		return 0;
	}
	
	public void updateSale(int saleId, int productCode, double qty){
		Update statement = Update.
				table(sales).
				set(s -> s.setDouble("quantity", qty)).
				where(s -> s.getInt("saleId")==saleId);
		statement.executeUpdate(); //TODO still working on this
				
				
	}
	
}
