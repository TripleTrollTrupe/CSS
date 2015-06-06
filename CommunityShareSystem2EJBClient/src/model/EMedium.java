package model;

import java.io.File;
import java.io.Serializable;

public abstract class EMedium implements Serializable {
	private static final long serialVersionUID = 6230406891147889779L;
	
	public abstract int getID();
	public abstract File getFile ();
	public abstract String getTitle ();
	public abstract String getAuthor();
	public abstract String getMimeType();
	public abstract Iterable<String> getTags();
	public abstract EMediumType getType();
	public abstract boolean canBookmarkPage();
	public abstract boolean canAnnotate();
	public abstract boolean canAnnotatePage();
	public abstract EMediumProperties getEMediumProperties();
	public abstract boolean isExpired();
	public abstract EMedium getLendable();
	public abstract EMedium makeRental();
}
