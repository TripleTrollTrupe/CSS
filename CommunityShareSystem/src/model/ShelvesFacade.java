package model;

import java.util.LinkedList;

import javax.naming.OperationNotSupportedException;
import javax.persistence.Embedded;
import javax.persistence.Table;

import model.events.EMediaCollectionListener;
import model.events.ShelfCollectionListener;
import model.rentals.Rental;
import model.rentals.RentalFactory;
import model.shelves.Shelf;
import model.shelves.Shelves;
import model.shelves.criteria.Criterion;

@Table
public class ShelvesFacade {
	
		@Embedded
		private Shelves shelves;
		private RentalFactory rentalFactory;
		
		public ShelvesFacade (Shelves shelves, RentalFactory rentalFactory) {
			this.shelves = shelves;
			this.rentalFactory = rentalFactory;
		}
		
		public boolean addNormalShelf (String name) {
			return shelves.addNormalShelf (name);
		}
		
		public boolean addSmartShelf (String name, Criterion criteria) {
			return shelves.addSmartShelf (name, criteria);
		}
		
		public Iterable<String> getShelves () {
			LinkedList <String> result = new LinkedList<String>();
			for (Shelf s : shelves)
				result.add(s.getName());
			return result;
		}

		public void removeShelf(String name) {
			shelves.removeShelf (name);
		}
		
		public void removeRental (String selfName, EMedium rental) throws OperationNotSupportedException {
			shelves.removeRentalFromShelf (selfName, (Rental) rental);
		}

		// pre: !isRented(rental)
		public boolean addRental(EMedium rental) {
			return shelves.addRental(rentalFactory.createRental(rental));
		}

		public boolean addShelfRental(String shelfName, EMedium rental) throws OperationNotSupportedException {
			return shelves.addRentalToShelf (shelfName, rentalFactory.createRental(rental));
		}

		public Iterable<EMedium> getShelfRentals (String shelfName) {
			LinkedList<EMedium> emedia = new LinkedList<EMedium> ();
			for (Rental r : shelves.getShelfRentals(shelfName))
				emedia.add(r);
			return emedia; 
		}
		
		public Iterable<EMedium> getRentals () {
			LinkedList<EMedium> emedia = new LinkedList<EMedium> ();
			for (Rental r : shelves.getRentals())
				emedia.add(r);
			return emedia; 
		}
		
		public boolean isRented(EMedium rental) {
			return shelves.isRented(rentalFactory.createRental(rental));
		}
		
		public boolean isRentalExpired(EMedium rental) {
			return shelves.isExpired(rentalFactory.createRental(rental));
		}		
		
		public void addShelfCollectionListener(ShelfCollectionListener listener) {
			shelves.addShelfCollectionListener(listener);
	    }

	    public void removeShelfCollectionListener(ShelfCollectionListener listener) {
	    	shelves.removeShelfCollectionListener(listener);
	    }
	    
	    public void addRentalCollectionListener(String shelfName, EMediaCollectionListener listener) {
	    	shelves.addRentalCollectionListener(shelfName, listener);
	    }

	    public void removeRentalCollectionListener(String shelfName, EMediaCollectionListener listener) {
	    	shelves.removeRentalCollectionListener(shelfName, listener);
	    }
}
