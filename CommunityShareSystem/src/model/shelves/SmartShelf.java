package model.shelves;

import java.util.Iterator;
import java.util.LinkedList;

import javax.naming.OperationNotSupportedException;
import javax.persistence.Table;

import model.events.RentalCollectionEvent;
import model.rentals.Rental;
import model.shelves.criteria.Criterion;

@Table
public class SmartShelf extends Shelf {

	private Criterion criteria;
	
	private Shelf myRentals;
	
	public SmartShelf(){
		super();
	}
	
	public SmartShelf(String name, Shelf myRentals, Criterion criteria) {
		super(name);
		this.myRentals = myRentals;
		this.criteria = criteria;
	}

	@Override
	public Iterator<Rental> iterator() {
		LinkedList<Rental> result = new LinkedList<Rental> ();
		for (Rental r : myRentals)
			if (criteria.satisfies(r))
				result.add(r);
		return result.iterator();
	}

	@Override
	public boolean addRental(Rental rental)
			throws OperationNotSupportedException {
		throw new OperationNotSupportedException ();
	}

	@Override
	public boolean removeRental(Rental rental)
			throws OperationNotSupportedException {
		throw new OperationNotSupportedException ();
	}

	@Override
	public void RentalAdded(RentalCollectionEvent event) {
		// nothing to be done
	}

	@Override
	public void RentalRemoved(RentalCollectionEvent event) {
		// nothing to be done
	}

}
