package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.rowset.FilteredRowSet;

import services.persistence.PersistenceException;
import services.persistence.inMemory.RDBMS.Insert;
import services.persistence.inMemory.RDBMS.Select;
import services.persistence.inMemory.RDBMS.Table;
import business.DiscountType;

import com.sun.rowset.FilteredRowSetImpl;

/**
 * Table Data Gateway for the discount's table
 *
 * Remarks:
 * 1. This class is implemented as a singleton. This is one of the possible
 * ways to go, but, for instance, implement all methods as static 
 * in another option. None of the approaches is strictly object oriented and
 * is going to complicate things in future should the application becomes more complex.
 * 
 * 2. The implementation decisions made in this version favour the understanding
 * of the table gateway pattern over performance and other issues discussed above.
 * In version 2.0 I am going to re-implement it fixing these issues, but, of course,
 * the implementation is far more complex.  
 * 
 * @author fmartins
 * @version 1.1 (5/10/2014)
 *
 */
public class CustomerTableGateway {
	
	/**
	 * The (in memory) customers' table
	 */
	private Table customers;
	
	
	public CustomerTableGateway(Table customer) {
		this.customers = customer;
	}

	/**
	 * Gets a customer by its Id number
	 * 
	 * @param customerId The id of the customer to search for
	 * @return The result set of the query
	 */
	public ResultSet find(int customerId) {
		return Select.
				from(customers).
				where(p -> p.getInt("id") == customerId).
				executeQuery();
	}

	/**
	 * Gets a customer by its VAT number 
	 * 
	 * @param vat The VAT number of the customer to search for
	 * @return The result set of the query
	 * @throws PersistenceException 
	 */
	public FilteredRowSet findWithVATNumber (int vat) throws PersistenceException {
		ResultSet rs =
			Select.
				from(customers).
				where(p -> p.getInt("vat") == vat).
				executeQuery();
		FilteredRowSet frs;
		try {
			frs = new FilteredRowSetImpl();
			frs.populate(rs);
			return frs;
		} catch (SQLException e) {
			throw new PersistenceException("Error finding a customer with VAT number " + vat);
		}
	}
	 
	
	/**
	 * TODO: fmartins: Check the "not in the database part"
	 * Adds a new customer provided that its VAT is not in the database
	 * 
	 * @param vat The NPC of the customer
	 * @param designation The customer's name
	 * @param phoneNumber The customer's phone number
	 * @param discountType The discount type for the customer
	 */
	public void addCustomer (int vat, String designation, 
			int phoneNumber, DiscountType discountType) {
		// create statement
		Insert statement = Insert.
				into(customers).
				value("vat", vat).
				value("designation", designation).
				value("phoneNumber", phoneNumber).
				value("discountId", DiscountTableGateway.discountTypeToDiscountId(discountType));
		// execute statement
		statement.executeUpdate();
	}

	public void update(FilteredRowSet rs) {
		// since it is a memory database, there is nothing to be done; otherwise, it must synchronize 
		// the result set with the database, maybe by calling the addCustomer method, for instance.
	}
}
