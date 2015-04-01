 package persistence;

import java.sql.ResultSet;

import services.persistence.inMemory.RDBMS.Select;
import services.persistence.inMemory.RDBMS.Table;

/**
 * Table gateway for table configuration.
 * 
 * Please read the remarks in the CustomerTableGateway class
 * 
 * @author fmartins
 * @version 1.1 (5/10/2014)
 *
 */
public class ConfigurationTableGateway {
	
	/**
	 * The (in memory) configuration table  
	 */
	private Table configurations;
	
	public ConfigurationTableGateway (Table configurations) {
		this.configurations = configurations;
	}

	/**
	 * Gets configuration settings
	 * 
	 * @return The result set of the query
	 */
	public ResultSet getConfigurationSettings () {
		return Select.
				from(configurations).
				executeQuery();
	}
	
}
