package css;

import model.EMedium;
import model.events.EMediumEvent;
import delegates.EMediumMetadataUIDelegate;

/**
 * The document's metadata ui delegate default implementation
 * 
 * @author fmartins, mguimas
 *
 */
public class CSSEMediumMetadataUIDelegate extends EMediumMetadataUIDelegate {

	public CSSEMediumMetadataUIDelegate () {
	}
	
	@Override
	public void setEMedium (EMedium eMedium) {
	}
	
	@Override
	public EMedium getEMedium() {
		return null;
	}
	
	@Override
	public void setObservers() {
	}
	
	@Override
	public void deleteObservers() {
	}	

	@Override
	public Iterable<Integer> getDocumentBookmarks() {
		return null;
	}

	@Override
	public Iterable<String> getPageAnnotations(int pageNum) {
		return null;
	}

	@Override
	public String getDocumentTitle() {
		return null;
	}

	@Override
	public void addAnnotation(int pageNum, String text) throws Exception {
	}

	@Override
	public void removeAnnotation(int pageNum, int annotNum) throws Exception {
	}

	@Override
	public void toggleBookmark(int pageNum) throws Exception {
	}

	@Override
	public String getAnnotationText(int pageNum, int annotNum) {
		return "";
	}

	@Override
	public void annotationAdded(EMediumEvent event) {
	}

	@Override
	public void annotationRemoved(EMediumEvent event) {
	}

	@Override
	public void bookmarkToggled(EMediumEvent event) {
	}

}
