package model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public enum EMediumType {
	DOCUMENT("Document"), SONG("Song"), IMAGE("Image");

	@Column
	@Enumerated(EnumType.STRING)
	private String description;

	private EMediumType (String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return description;
	}
}
