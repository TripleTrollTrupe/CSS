package css;

import java.util.LinkedList;

import model.EMedium;
import model.EMediumProperties;
import model.events.ShelfCollectionEvent;
import model.lendables.Lendable;
import model.lendables.Library;
import model.shelves.Shelves;
import delegates.BookshelfUIDelegate;

/**
 * The bookshelf ui delegate default implementation
 * 
 * @author fmartins, mguimas
 *
 */
public class CSSBookshelfUIDelegate extends BookshelfUIDelegate {

	private Shelves shelves;
	private Library library;
	
	public CSSBookshelfUIDelegate(Shelves shelves, Library library) {
		this.shelves = shelves;
		this.library = library;
		shelves.addShelfCollectionListener(this);
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
		for (Lendable r : library)
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
		if (shelves.returnRental(shelfName, eMedium))
			returnEMediumFromPanel(eMedium);
	}
	
	@Override
	public EMediumProperties readEMediumProperties(EMedium eMedium) throws Exception {
		return library.readProperties(eMedium); 	
	}
	
	@Override
	public boolean revokeLending(EMedium eMedium) throws Exception {
		return library.revokeLending(eMedium); 	
	}

	@Override
	public boolean addEMediumLibrary(EMediumProperties lendableProperties) {
		boolean result = library.addLendable(lendableProperties);
		if (result)
			addToEMediaPanel(AppProperties.INSTANCE.LIBRARY_NAME, 
					library.getLastAddedLendable());
		return result;
	}

	@Override
	public boolean canBeViewed(EMedium eMedium) throws Exception {
		return shelves.canBeViewed(eMedium);
	}

	@Override
	public void shelfSelected(String selectedShelfName) {
		// Do nothing		
	}

}
