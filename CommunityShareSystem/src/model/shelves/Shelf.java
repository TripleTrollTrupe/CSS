package model.shelves;

import javax.naming.OperationNotSupportedException;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.swing.event.EventListenerList;

import model.events.EMediaCollectionListener;
import model.rentals.Rental;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity @Inheritance(strategy = InheritanceType.SINGLE_TABLE) public abstract class Shelf implements Iterable<Rental>, 
					Comparable<Shelf>, EMediaCollectionListener {

	@Id @GeneratedValue(strategy = SEQUENCE) private int id;
	
	@Column	private String name;
	
	protected EventListenerList listeners;
	
	public Shelf (String name) {
		this.name = name;
		listeners = new EventListenerList();
	}
	
	public Shelf() {
		
	}

	public String getName () {
		return name;
	}
	
	@Override
	public int compareTo (Shelf other) {
		return name.compareTo(other.name);
	}
	
	public abstract boolean addRental (Rental rental)
		throws OperationNotSupportedException;
	
	public abstract boolean removeRental (Rental rental)
		throws OperationNotSupportedException;
	
	@Override
	public String toString () {
		return name;
	}
	
	public void addRentalCollectionListener(EMediaCollectionListener listener) {
        listeners.add(EMediaCollectionListener.class, listener);
    }

    public void removeRentalCollectionListener(EMediaCollectionListener listener) {
        listeners.remove(EMediaCollectionListener.class, listener);
    }
}
