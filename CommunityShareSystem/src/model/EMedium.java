package model;

import java.io.File;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Inheritance;

import model.events.EMediumListener;

@Inheritance
@Embeddable
public interface EMedium extends Comparable<EMedium> {
	
	@Embedded
	File getFile ();
	
	@Embedded
	String getTitle ();
	
	@Embedded
	String getAuthor();
	
	@Embedded
	String getMimeType();
	
	
	Iterable<String> getTags();
	
	@Embedded
	EMediumType getType();
	
	void addEMediumListener(EMediumListener listener);
	void removeEMediumListener(EMediumListener listener);
	boolean canBookmarkPage();
	boolean canAnnotate();
	boolean canAnnotatePage();
	EMediumPropertiesData getEMediumProperties();
	boolean isExpired();
}
