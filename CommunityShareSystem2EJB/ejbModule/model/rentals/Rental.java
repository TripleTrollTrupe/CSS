package model.rentals;

import java.io.File;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import model.EMedium;
import model.EMediumProperties;
import model.EMediumType;
import model.lendables.Lendable;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Rental extends EMedium {
	private static final long serialVersionUID = -1704573469652777871L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne(cascade={CascadeType.REFRESH, CascadeType.MERGE})
	@JoinColumn(nullable=false)
	private Lendable lendable;

	/**
	 * The instant the lendable was borrowed. 
	 */
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	private Date timestamp;
	
	/**
	 * True if it is not currently borrowed, otherwise false 
	 */
	@Column(nullable=false)
	private boolean expired;
	
	@ElementCollection
	private List<String> annotations;
	
	Rental() {
	}
	
	public Rental(Lendable lendable) {
		this.lendable = lendable;
		timestamp = new Date();
		annotations = new LinkedList<String>();
	}
	
	public String getAuthor() {
		return lendable.getAuthor();
	}
		
	public Date getRentalTimestamp() {
		return timestamp;
	}

	public int addAnnotation(String text) {
		annotations.add(text);
		return annotations.size();
	}
	
	// @requires a valid annotNum
	public void removeAnnotation(int annotNum) {
		annotations.remove(annotNum);
	}

	public List<String> getAnnotations() {
		return annotations;
	}

	public String getAnnotationText(int annotNum) {
		return annotations.get(annotNum);
	}

	public boolean hasAnnotations() {
		return !annotations.isEmpty();
	}

	public File getFile() {
		return lendable.getFile();
	}

	public String getMimeType() {
		return lendable.getMimeType();
	}

	@Override
	public String getTitle() {
		return lendable.getTitle();
	}

	@Override
	public List<String> getTags() {
		return lendable.getTags();
	}

	@Override
	public boolean canBookmarkPage() {
		return false;
	}

	@Override
	public boolean canAnnotate() {
		return true;
	}

	@Override
	public boolean canAnnotatePage() {
		return true;
	}

	@Override
	public EMediumType getType() {
		return lendable.getType();
	}

	@Override
	public EMediumProperties getEMediumProperties() {
		return lendable.getEMediumProperties();
	}

	/**
	 * This is called when moving the lendable again to MyRentals
	 */
	public void renew() {
		expired = false;
	}
	
	public boolean returnToLibrary() {
		boolean result = !expired;
		if (result) {
			lendable.revokeLending();
			expired = true;
		}
		return result;
	}
	
	@Override
	public boolean isExpired() {
		return expired;
	}

	@Override
	public Lendable getLendable() {
		return lendable;
	}

	@Override
	public Rental makeRental() {
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result
				+ ((lendable == null) ? 0 : lendable.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rental other = (Rental) obj;
		if (id != other.id)
			return false;
		if (lendable == null) {
			if (other.lendable != null)
				return false;
		} else if (!lendable.equals(other.lendable))
			return false;
		return true;
	}

	@Override
	public int getID() {
		return id;
	}

}
