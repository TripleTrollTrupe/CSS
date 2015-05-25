package model.lendables;

import java.util.Iterator;

import javax.persistence.EntityManager;

import model.EMedium;
import model.EMediumAttribute;
import model.EMediumProperties;
import model.shelves.Shelves;
import persistence.utils.BusinessOperationWithResult;
import persistence.utils.JPAUtils;

public class Library implements Iterable<Lendable> {

	private Shelves shelves;
	private Lendable lastAddedLendable;
	
	public Library(Shelves shelves) {
		this.shelves = shelves;
	}

	public boolean addLendable(EMediumProperties properties) {
		try {
			lastAddedLendable = JPAUtils.runInTxWithResult(new BusinessOperationWithResult() {
				@Override
				public Lendable execute(EntityManager em) throws Exception {
					Lendable lendable = null;
					if (!existsLendable(em, properties)) {
						lendable = new Lendable (properties);
						JPAUtils.persist(em, lendable);
					}
					return lendable;
				}
			});
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean revokeLending(EMedium eMedium) throws Exception {
		return shelves.revokeLending((Lendable) eMedium);
	}
	
	private boolean existsLendable(EntityManager em, EMediumProperties properties) {
		String path = properties.getAttribute(EMediumAttribute.PATH);
		for (Lendable lendable : this)
			if (lendable.getPath().equals(path))
				return true;
		return false;
	}
	
	@Override
	public Iterator<Lendable> iterator() {
		return JPAUtils.findList(Lendable.FIND_ALL, Lendable.class).iterator();
	}
	
	public Lendable getLastAddedLendable() {
		return lastAddedLendable;
	}

	public EMediumProperties readProperties(EMedium eMedium) throws Exception {
		return JPAUtils.runInTxWithResult(new BusinessOperationWithResult() {
			@Override
			public EMediumProperties execute(EntityManager em) throws Exception {
				Lendable lendable = (Lendable) em.merge(eMedium);
				em.refresh(lendable);
				return lendable.getEMediumProperties();
			}
		});
	}
 	
}
