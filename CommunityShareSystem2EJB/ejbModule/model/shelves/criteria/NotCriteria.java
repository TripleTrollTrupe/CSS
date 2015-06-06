package model.shelves.criteria;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import model.EMedium;

@Entity
public final class NotCriteria extends Criterion {

	@OneToOne @JoinColumn(nullable=false)
	private Criterion criteria;
	
	NotCriteria() {}
	
	public NotCriteria(Criterion criteria) {
		this.criteria = criteria;
	}

	@Override
	public boolean satisfies(EMedium rental) {
		return !criteria.satisfies(rental);
	}

}
