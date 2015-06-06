package model.shelves;

import javax.ejb.Local;

import model.EMedium;
import model.shelves.criteria.Criterion;

@Local
public interface ShelvesHandlerLocal extends ShelvesHandlerRemote {
	public boolean addSmartShelf (String name, Criterion criteria) throws Exception;
	public boolean returnRental (String shelfName, EMedium rental) throws Exception;
	public boolean canBeViewed(EMedium eMedium) throws Exception;
	public boolean revokeLending(EMedium lendable) throws Exception;
}
