 package controller.swing;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import view.Dialogs;
import view.swing.DocumentUIFullscreen;
import view.swing.EMediumLabel;
import view.swing.EMediumMetadataUI;
import view.swing.EMediumShowUI;
import delegates.EMediumMetadataUIDelegate;
import delegates.EMediumUIDelegate;

public class EMediumShowController {

	private EMediumUIDelegate eMediumUIDelegate;
	private EMediumShowUI eMediumShowUI;
	private EMediumMetadataUIDelegate eMediumMetadataUIDelegate;
	private EMediumLabel eMediumLabel;
	private JFrame frame;
	
	public EMediumShowController(EMediumUIDelegate eMediumUIDelegate,
			EMediumMetadataUIDelegate eMediumMetadataUIDelegate,
			EMediumLabel eMediumLabel) {
		this.eMediumUIDelegate = eMediumUIDelegate;
		this.eMediumMetadataUIDelegate = eMediumMetadataUIDelegate;
		this.eMediumLabel = eMediumLabel;
	}
	
	/**
	 * Links the delegate back to its UI
	 * 
	 * @param bookshelf The bookshelf UI
	 */
	public void setEMediumShowUI (EMediumShowUI eMediumShowUI, JFrame frame) {
		this.eMediumShowUI = eMediumShowUI;
		this.frame = frame;
	}

	
	public MouseAdapter bookmarkAction() {
		return new MouseAdapter () {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					eMediumUIDelegate.toggleBookmark (eMediumShowUI.getCurrentPage());
				} catch (Exception e) {
					Dialogs.showException("Failure toggling bookmark", e);
				}
			}
		};
	}
	
	public MouseAdapter pageSelection() {
		return new MouseAdapter () {
			@Override
			public void mouseClicked(MouseEvent event) {
				if (event.getButton() == MouseEvent.BUTTON1) 
					eMediumShowUI.pageDown();
        		else if(event.getButton() == MouseEvent.BUTTON3) 
        			eMediumShowUI.pageUp();
			}
		};
	}
	

	public MouseAdapter closeAction() {
		return new MouseAdapter () {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				eMediumLabel.setEMedium(eMediumUIDelegate.getEMedium());
				eMediumUIDelegate.deleteObservers();
				eMediumUIDelegate.setLastPageVisited(eMediumShowUI.getCurrentPage());
				eMediumShowUI.closeEMediumShowUI();
			}

		};
	}


	public MouseAdapter pageAction() {
		return new MouseAdapter () {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				eMediumMetadataUIDelegate.setEMedium(eMediumLabel.getEMedium());
				eMediumMetadataUIDelegate.setObservers();
				new EMediumMetadataUI (frame, eMediumShowUI.getCurrentPage(), 
						eMediumMetadataUIDelegate, eMediumShowUI);
				eMediumLabel.setEMedium(eMediumMetadataUIDelegate.getEMedium());
				eMediumUIDelegate.setEMedium(eMediumMetadataUIDelegate.getEMedium());
				eMediumMetadataUIDelegate.deleteObservers();
			}
		};
	}


	public MouseAdapter fullScreenAction() {
		return new MouseAdapter () {
			@Override
			public void mouseClicked(MouseEvent arg0) {
		    	frame.setVisible(false);
		    	new DocumentUIFullscreen(eMediumLabel.getEMediumViewer(), 
		    			eMediumLabel.getSlideDuration(), eMediumShowUI);
			}
		};
	}

}