package controller.swing;

import model.EMedium;
import model.EMediumProperties;
import view.IBookshelfUI;
import view.swing.EMediumLabel;
import view.swing.EMediumMetadataUI;
import view.swing.EMediumShowUI;
import delegates.BookshelfUIDelegate;
import delegates.EMediumMetadataUIDelegate;
import delegates.EMediumUIDelegate;

public class BookshelfEMediaControllerImpl extends BookshelfEMediaController {
	
	/**
	 * Reference to the UI's delegate
	 */
	private BookshelfUIDelegate bookshelfUIDelegate;
	private EMediumUIDelegate eMediumUIDelegate;
	private EMediumMetadataUIDelegate eMediumMetadataUIDelegate;
	
	public BookshelfEMediaControllerImpl(BookshelfUIDelegate bookshelfUIDelegate,
			EMediumUIDelegate eMediumUIDelegate, 
			EMediumMetadataUIDelegate eMediumMetadataUIDelegate) {
		this.bookshelfUIDelegate = bookshelfUIDelegate;
		this.eMediumUIDelegate = eMediumUIDelegate;
		this.eMediumMetadataUIDelegate = eMediumMetadataUIDelegate;
	}
	
	@Override
	public boolean revokeLending(EMedium eMedium) throws Exception {
		return bookshelfUIDelegate.revokeLending(eMedium);
	}

	@Override
	public EMediumProperties readEMediumProperties(EMedium eMedium) throws Exception {
		return bookshelfUIDelegate.readEMediumProperties(eMedium);
	}
	
	@Override
	public void returnEMediumShelf(String shelfName, EMedium eMedium) throws Exception {
		bookshelfUIDelegate.returnEMediumShelf(shelfName, eMedium);
	}

	@Override
	public void removeEMediumShelf(String shelfName, EMedium eMedium) throws Exception {
		bookshelfUIDelegate.removeRental(shelfName, eMedium);
	}

	@Override
	public void updateRental(EMedium eMedium, EMediumProperties eMediumProperties) {
		bookshelfUIDelegate.updateRental (eMedium, eMediumProperties);
	}

	@Override
	public void eMediumShow(EMediumLabel selectedEMediaLabel) {
		eMediumUIDelegate.setEMedium(selectedEMediaLabel.getEMedium());
		eMediumUIDelegate.setObservers();
		new EMediumShowUI (bookshelfUI,
				selectedEMediaLabel.getEMediumViewer(), 
				new EMediumShowController(eMediumUIDelegate, eMediumMetadataUIDelegate, selectedEMediaLabel),
				eMediumUIDelegate);
		selectedEMediaLabel.setEMedium(eMediumUIDelegate.getEMedium());
	}

	@Override
	public boolean canBeViewed(EMedium eMedium) throws Exception {
		return bookshelfUIDelegate.canBeViewed(eMedium);
	}

	@Override
	public void metadataShow(EMediumLabel selectedEMediaLabel, IBookshelfUI bookshelfUI) {
		eMediumMetadataUIDelegate.setEMedium(selectedEMediaLabel.getEMedium());
		eMediumMetadataUIDelegate.setObservers();
		new EMediumMetadataUI (bookshelfUI, 1, // show metadata over the first page
				eMediumMetadataUIDelegate, null);
		selectedEMediaLabel.setEMedium(eMediumMetadataUIDelegate.getEMedium());
		eMediumMetadataUIDelegate.deleteObservers();
	}
	
}
