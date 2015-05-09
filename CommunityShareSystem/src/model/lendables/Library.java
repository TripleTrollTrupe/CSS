package model.lendables;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

import model.EMedium;
import model.EMediumPropertiesData;
import model.EMediumType;

@Table
public class Library implements Iterable<Lendable> {

	// need the Map from lendable to lendable 
	// because I want to sure I change the lendable in the
	// library and not other "equal" to it.
	@MapKeyColumn
	private Map<Lendable, Lendable> lendables;
	
	@Column(nullable = true) private Lendable lastAddedLendable;
		
	public Library () {
		lendables = new HashMap<Lendable, Lendable> ();
	}
	
	public boolean addLendable(EMediumType type, EMediumPropertiesData properties) {
		Lendable aboutToBeAdded = new Lendable(type, properties);
		if (!lendables.containsKey(aboutToBeAdded)) {
			lastAddedLendable = aboutToBeAdded;
			lendables.put(aboutToBeAdded, aboutToBeAdded);
			return true;
		} else
			return false;
	}
	
	@Override
	public Iterator<Lendable> iterator() {
		return lendables.values().iterator();
	}

	public Lendable getLastAddedLendable() {
		return lastAddedLendable;
	}

	// pre: canBeRent(eMedium)
	public void rent(EMedium eMedium) {
		lendables.get(eMedium).rent();
	} 
	
	public boolean canBeRent(EMedium eMedium) {
		Lendable toBeRented = lendables.get(eMedium);
		return toBeRented != null && toBeRented.hasLicensesAvailable();
	}
 	
}
