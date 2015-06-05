package model;

import java.io.File;

import model.rentals.Rental;

public interface EMedium {
	File getFile ();
	String getTitle ();
	String getAuthor();
	String getMimeType();
	Iterable<String> getTags();
	EMediumType getType();
	boolean canBookmarkPage();
	boolean canAnnotate();
	boolean canAnnotatePage();
	EMediumProperties getEMediumProperties();
	boolean isExpired();
	EMedium getLendable();
	Rental makeRental();
}
