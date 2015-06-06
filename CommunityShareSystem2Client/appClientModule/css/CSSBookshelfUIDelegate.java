package css;

import java.util.LinkedList;

import model.EMedium;
import model.EMediumProperties;
import model.events.ShelfCollectionEvent;
import model.lendables.LibraryHandlerRemote;
import model.shelves.ShelvesHandlerRemote;
import delegates.BookshelfUIDelegate;

/**
 * The bookshelf ui delegate default implementation
 * 
 * @author fmartins, mguimas
 *
 */
@SuppressWarnings("all")
public class CSSBookshelfUIDelegate extends BookshelfUIDelegate {

	private ShelvesHandlerRemote shelves;
	private LibraryHandlerRemote library;
	
	private WebService.Shelves.ShelvesHandlerRemote shelves1;
	private  WebService.Library.LibraryHandlerRemote library1;
	
	public CSSBookshelfUIDelegate(WebService.Shelves.ShelvesHandlerRemote shelves, WebService.Library.LibraryHandlerRemote library) {
		this.shelves1 = shelves;
		this.library1 = library;
	}

	@Override
	public Iterable<EMedium> getShelfRentals(String shelfName) throws Exception {
		return shelves.getShelfRentals(shelfName);
	}
	
	@Override
	public Iterable<EMedium> getRentals() throws Exception {
		return shelves.getRentals();
	}

	@Override
	public Iterable<EMedium> getLibraryEMedia() {
		LinkedList<EMedium> emedia = new LinkedList<EMedium> ();
		for (EMedium r : library.getAllLendables())
			emedia.add(r);
		return emedia; 
	}

	@Override
	public boolean addNormalShelf(String shelfName) {
		return shelves.addNormalShelf(shelfName);
	}

	@Override
	public void removeShelf(String shelfName) throws Exception {
		shelves.removeShelf (shelfName);
	}

	@Override
	public void updateRental(EMedium document,
			EMediumProperties documentProperties) {
		throw new UnsupportedOperationException();
		//library.updateDocument (document, documentProperties);
	}

	@Override
	public void removeRental(String shelfName, EMedium rental) throws Exception {
		shelves.removeRental(shelfName, rental);
		removeEMediumFromPanel(rental);
	}
	
	@Override
	public Iterable<String> getShelves() throws Exception {
		return shelves.getShelves();
	}
	
	@Override
	public boolean rentLendable(EMedium lendable) throws Exception {
		return shelves.addOrRenewRental(lendable);
	}

	@Override
	public void addRental(String shelfName, EMedium rental) throws Exception {
		shelves.addRental(shelfName, rental);
	}

	@Override
	public void shelfAdded(ShelfCollectionEvent event) {
		addShelfTreeNode((String) event.getSource());
	}

	@Override
	public void shelfRemoved(ShelfCollectionEvent event) {
		removeShelfTreeNode((String) event.getSource());
	}

	@Override
	public void returnEMediumShelf(String shelfName, EMedium eMedium) throws Exception  {
	}
	
	@Override
	public EMediumProperties readEMediumProperties(EMedium eMedium) throws Exception {
		return new EMediumProperties();
	}
	
	@Override
	public boolean revokeLending(EMedium eMedium) throws Exception {
		return false;
	}

	@Override
	public boolean addEMediumLibrary(EMediumProperties lendableProperties) {
		return false;
	}

	@Override
	public boolean canBeViewed(EMedium eMedium) throws Exception {
		return false;
	}

	@Override
	public void shelfSelected(String selectedShelfName) {	
	}

}
