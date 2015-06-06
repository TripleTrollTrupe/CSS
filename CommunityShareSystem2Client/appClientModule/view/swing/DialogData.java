package view.swing;

import model.EMediumAttribute;
import model.EMediumProperties;

public class DialogData {
	public final EMediumProperties data;
	private boolean accepted;
	
	public DialogData() {
		this.data = new EMediumProperties();
	}
	
	public DialogData(EMediumProperties data) {
		this.data = data;
	}

	public <T> T getAttribute(EMediumAttribute attribute) {
		return data.getAttribute(attribute);
	}

	public void addAttribute(EMediumAttribute attribute, Object value) {
		data.addAttribute(attribute, value);
	}
	
	public boolean didUserAccept() {
		return accepted;
	}

	public void userAccepted(boolean choice) {
		accepted = choice;
	}
}