package view.swing;

import model.EMediumAttribute;
import model.EMediumPropertiesData;
import model.EMediumValue;

public class DialogData {
	public final EMediumPropertiesData data;
	private boolean accepted;
	

	public DialogData() {
		this.data = new EMediumPropertiesData();
	}
	
	public DialogData(EMediumPropertiesData data) {
		this.data = data;
	}

	public Object getAttribute(EMediumAttribute attribute) {
		return data.getAttribute(attribute);
	}

	public void addAttribute(EMediumAttribute attribute, Object value) {
		@SuppressWarnings("rawtypes")
	    EMediumValue valueObj = new EMediumValue(value);
	    data.addAttribute(attribute, valueObj);
	}
	
	public boolean didUserAccept() {
		return accepted;
	}

	public void userAccepted(boolean choice) {
		accepted = choice;
	}
}