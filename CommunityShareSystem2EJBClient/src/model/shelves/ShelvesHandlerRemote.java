package model.shelves;

import java.util.List;

import javax.ejb.Remote;
import javax.jws.WebService;

import model.EMedium;

@Remote
@WebService
public interface ShelvesHandlerRemote {
	public List<String> getShelves () throws Exception;
	public boolean addNormalShelf(String name);
	public void removeShelf(String name) throws Exception;
	public List<EMedium> getRentals () throws Exception;
	public List<EMedium> getShelfRentals (String shelfName) throws Exception;
	public void addRental(String shelfName, EMedium rental) throws Exception;
	public boolean addOrRenewRental(EMedium lendable);	
	public boolean removeRental(String shelfName, EMedium rental) throws Exception;
}
