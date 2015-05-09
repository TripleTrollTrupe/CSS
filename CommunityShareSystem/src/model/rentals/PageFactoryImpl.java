package model.rentals;

import javax.swing.event.EventListenerList;

public class PageFactoryImpl implements PageFactory {
	
	public Object createPage(){
		return new Page();
	}

	@Override
	public Object createPage(int pageNum, EventListenerList listeners) {
		return new Page(pageNum,listeners);
	}

}
