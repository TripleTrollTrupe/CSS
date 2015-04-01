package business;

import persistence.Persistence;

public class TableModule {

	protected Persistence persistence;
	
	public TableModule (Persistence persistence) {
		this.persistence = persistence;
	}

}
