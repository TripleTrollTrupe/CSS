package model.shelves.criteria;

import javax.persistence.Embeddable;

import model.rentals.Rental;

@Embeddable
public interface Criterion {
	boolean satisfies (Rental rental);
}
