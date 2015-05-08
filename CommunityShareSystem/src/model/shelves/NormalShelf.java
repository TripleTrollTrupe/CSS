package model.shelves;

import static javax.persistence.CascadeType.ALL;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.Entity;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;

import model.events.EMediaCollectionListener;
import model.events.RentalCollectionEvent;
import model.rentals.Rental;

@Entity
public class NormalShelf extends Shelf {
	
	// need the Map from Rental to Rental 
	// because I want to sure I change the Rental in the
	// Shelf and not other "equal" to it.
	
	@OneToMany(cascade = ALL)
    @MapKeyColumn(name="RENTALS")
	private Map<Rental, Rental> rentals;
	
	public NormalShelf(){
		super();
		rentals = new TreeMap<Rental,Rental> ();
	}

	public NormalShelf (String name) {
		super(name);
		rentals = new TreeMap<Rental, Rental> ();
	}

	public boolean addRental (Rental rental) {
		Rental myRental = rentals.get(rental);
		if (myRental != null)
			myRental.renew();
		else 
			rentals.put (rental, rental);
		fireRentalAdded (new RentalCollectionEvent (rental));
		return true;
	}
	
	public boolean removeRental (Rental rental) {
		boolean result = rentals.remove(rental) != null;
		fireRentalRemoved (new RentalCollectionEvent (rental));
		return result;
	}
	
	public boolean contains (Rental rental) {
		return rentals.containsKey(rental);
	}
	
	//pre: contains(rental)
	public boolean isExpired(Rental rental) {
		return rentals.get(rental).isExpired();
	}
	
	@Override
	public Iterator<Rental> iterator() {
		return rentals.values().iterator();
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
