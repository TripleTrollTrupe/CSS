package css;

import java.io.File;

import model.EMedium;
import delegates.EMediumUIDelegate;

/**
 * The e-media viewer ui delegate default implementation
 * 
 * @author fmartins, mguimas
 *
 */
public class CSSEMediaUIDelegate extends EMediumUIDelegate {
	
	public CSSEMediaUIDelegate () {
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
	public void setLastPageVisited(int pageNum) {
	}

	@Override
	public boolean isBookmarked(int pageNum) {
		return false;
	}

	@Override
	public int getLastPageVisited() {
		return 1;
	}

	@Override
	public File getEMediaFile() {
		return new File("");
	}

	@Override
	public boolean hasAnnotations(int pageNum) {
		return false;
	}

	@Override
	public void toggleBookmark(int pageNum) throws Exception {
	}

}
