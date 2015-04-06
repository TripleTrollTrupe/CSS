package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import services.persistence.inMemory.RDBMS.Insert;
import services.persistence.inMemory.RDBMS.Select;
import services.persistence.inMemory.RDBMS.Table;

public class SaleTableGateway {

	private Table sales;

	public SaleTableGateway(Table sales) {
		this.sales = sales;
	}

	public ResultSet getSaleByID(int saleID) {
		return Select.from(sales).where(s -> s.getInt("saleID") == saleID)
				.executeQuery();
	}

	public int addSale(int customerVAT) {
		int result = 0;
		Insert statement = Insert.into(sales).value("customer", customerVAT);
		// execute statement
		statement.executeUpdate();
		ResultSet rs = Select.from(sales)
				.where(s -> s.getInt("customer") == customerVAT).executeQuery();
		try {
			rs.last(); // the last row is the one with the biggest saleID?
			result = rs.getInt("saleID");
		} catch (SQLException e) {
		}
		return result;
	}

}
