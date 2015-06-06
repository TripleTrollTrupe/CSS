package model.shelves.criteria;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import model.EMedium;
import model.rentals.Rental;

@Entity
public final class RecentlyBorrowedCriteria extends Criterion {

	@Column(nullable=false)
	private long elapsedTime;

	RecentlyBorrowedCriteria() {}
	
	public RecentlyBorrowedCriteria (long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
	
	@Override
	public boolean satisfies(EMedium rental) {
		return ((Rental) rental).getRentalTimestamp().getTime() + elapsedTime > new Date().getTime() ;
	}
}
