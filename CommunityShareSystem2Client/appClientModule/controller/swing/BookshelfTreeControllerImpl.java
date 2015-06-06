package controller.swing;

import model.EMedium;
import delegates.BookshelfUIDelegate;

public class BookshelfTreeControllerImpl extends BookshelfTreeController {

	private BookshelfUIDelegate bookshelfUIDelegate;
	
	public BookshelfTreeControllerImpl(BookshelfUIDelegate bookshelfUIDelegate) {
		this.bookshelfUIDelegate = bookshelfUIDelegate;
	}
	
	@Override
	public Iterable<EMedium> getShelfRentals(String selectedShelfName) throws Exception {
		return bookshelfUIDelegate.getShelfRentals(selectedShelfName);
	}

	@Override
	public Iterable<EMedium> getRentals() throws Exception {
		return bookshelfUIDelegate.getRentals();
	}

	@Override
	public boolean addNormalShelf(String shelfName) {
		return bookshelfUIDelegate.addNormalShelf(shelfName);
	}

	@Override
	public Iterable<EMedium> getLibraryEMedia() {
		return bookshelfUIDelegate.getLibraryEMedia();
	}

	@Override
	public void removeShelf(String caption) throws Exception {
		bookshelfUIDelegate.removeShelf (caption);
	}

	@Override
	public void shelfSelected(String selectedShelfName) {
		bookshelfUIDelegate.shelfSelected (selectedShelfName);
	}

}
