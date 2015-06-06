package model.shelves.criteria;

import javax.persistence.Entity;

import model.EMedium;

@Entity
public final class AndCriteria extends CompoundCriteria {

	AndCriteria() {}
	
	public AndCriteria(Criterion leftCriteria, Criterion rightCriteria) {
		super(leftCriteria, rightCriteria);
	}

	@Override
	public boolean satisfies(EMedium document) {
		return leftCriteria.satisfies(document) && 
			   rightCriteria.satisfies(document);
	}

}
