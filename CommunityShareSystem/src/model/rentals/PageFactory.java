package model.rentals;

import javax.swing.event.EventListenerList;

public interface PageFactory {

	Object createPage();
	Object createPage(int pageNum, EventListenerList listeners);
}
