package css;


import view.StartupUI;
import WebService.Library.LibraryHandlerRemote;
import WebService.Library.LibraryService;
import WebService.Shelves.ShelvesHandlerRemote;
import WebService.Shelves.ShelvesService;

/**
 * The main project class
 * 
 * @author fmartins
 *
 */
public class StartUp {
	
	public static void main(String[] args) throws Exception {
		ShelvesHandlerRemote shelves = new ShelvesService().getShelvesPort();
		LibraryHandlerRemote library = new LibraryService().getLibraryPort();
		
		StartupUI.run(
				new CSSBookshelfUIDelegate(shelves, library), 
				new CSSEMediaUIDelegate(), 
				new CSSEMediumMetadataUIDelegate());
	}

}
