package ui.helpers;

import java.util.LinkedList;
import java.util.List;

import model.EMedium;

public class EMediumHelper {

	private String message;
	private List<EMedium> lendables;
	
	public EMediumHelper() {
		message = null;
		lendables = new LinkedList<EMedium>();
	}

	public List<EMedium> getEMediums () {
		return lendables;
	}
	
	public void addEMedium(EMedium emedium) {
		lendables.add(emedium);
	}
	
	public boolean isHasEMediums() {
		return lendables.size() != 0;
	}
	
	public void setMessage(String message) {
		this.message = message;	
	}
	
	public String getMessage() {
		return message;
	}
}
