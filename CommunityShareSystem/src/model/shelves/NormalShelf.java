package model.shelves;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Entity;
import javax.persistence.MapKeyColumn;

import model.events.EMediaCollectionListener;
import model.events.RentalCollectionEvent;
import model.rentals.Rental;

@Entity
public class NormalShelf extends Shelf {
	
	// need the Map from Rental to Rental 
	// because I want to sure I change the Rental in the
	// Shelf and not other "equal" to it.
	
	@MapKeyColumn @CollectionTable private List<Rental> rentals;

	public NormalShelf(){
		super();
		rentals = new ArrayList<Rental> ();
	}

	public NormalShelf (String name) {
		super(name);
		rentals = new ArrayList<Rental> ();
	}

	public boolean addRental (Rental rental) {
		Rental myRental = rentals.get(rentals.indexOf(rental));
		if (myRental != null)
			myRental.renew();
		else 
			rentals.add(rental);
		fireRentalAdded (new RentalCollectionEvent (rental));
		return true;
	}
	
	public boolean removeRental (Rental rental) {
		boolean result = rentals.remove(rentals.indexOf(rental)) != null;
		fireRentalRemoved (new RentalCollectionEvent (rental));
		return result;
	}
	
	public boolean contains (Rental rental) {
		return rentals.contains(rental);
	}
	
	//pre: contains(rental)
	public boolean isExpired(Rental rental) {
		return rentals.get(rentals.indexOf(rental)).isExpired();
	}
	
	@Override
	public Iterator<Rental> iterator() {
		return rentals.iterator();
	}

	@Override
	public void RentalAdded(RentalCollectionEvent event) {
		// Nothing to be done here!
	}

	@Override
	public void RentalRemoved(RentalCollectionEvent event) {
		rentals.remove(event.getSource());
	}
	
    private void fireRentalAdded(RentalCollectionEvent event) {
    	for (EMediaCollectionListener listener : listeners.getListeners(EMediaCollectionListener.class))
    		listener.RentalAdded(event);
    }

    private void fireRentalRemoved(RentalCollectionEvent event) {
    	for (EMediaCollectionListener listener : listeners.getListeners(EMediaCollectionListener.class))
    		listener.RentalRemoved(event);
    }
}
