package model.shelves.criteria;

import javax.persistence.Entity;

import model.EMedium;

@Entity
public final class OrCriteria extends CompoundCriteria {

	OrCriteria() {}
	
	public OrCriteria(Criterion leftCriteria, Criterion rightCriteria) {
		super(leftCriteria, rightCriteria);
	}

	@Override
	public boolean satisfies(EMedium rental) {
		return leftCriteria.satisfies(rental) || 
		   rightCriteria.satisfies(rental);
	}

}
