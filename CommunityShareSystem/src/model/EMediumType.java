package model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public enum EMediumType {
	DOCUMENT("Document"), SONG("Song"), IMAGE("Image");

	@Column
	private String description;

	private EMediumType (String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return description;
	}
}
