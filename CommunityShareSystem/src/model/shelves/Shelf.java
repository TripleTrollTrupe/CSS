package model.shelves;

import static javax.persistence.InheritanceType.SINGLE_TABLE;

import javax.naming.OperationNotSupportedException;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.swing.event.EventListenerList;

import model.events.EMediaCollectionListener;
import model.rentals.Rental;

@Entity
@Inheritance(strategy = SINGLE_TABLE)
public abstract class Shelf implements Iterable<Rental>, 
					Comparable<Shelf>, EMediaCollectionListener {
	
	@Id
	private int id;
	
	@Column
	private String name;
	
	protected EventListenerList listeners;
	
	public Shelf (String name) {
		this.name = name;
		listeners = new EventListenerList();
	}
	
	public Shelf() {
		// TODO Auto-generated constructor stub
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
