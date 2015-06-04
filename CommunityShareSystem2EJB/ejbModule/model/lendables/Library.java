package model.lendables;

import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.EMedium;
import model.EMediumProperties;
import model.shelves.Shelves;

@Stateless
public class Library implements Iterable<EMedium> {

	@EJB private Shelves shelves;
	@EJB private Lendable lastAddedLendable;
	
	@PersistenceContext private EntityManager em;
	
	public Library(Shelves shelves) {
		this.shelves = shelves;
	}

	public boolean addLendable(EMediumProperties properties) {
		if(existsLendable(em, properties)){
			Lendable lendable = new Lendable(properties);
			em.persist(lendable);
			lastAddedLendable = lendable;
			return true;
		}
		return false;
	}
	
	public boolean revokeLending(EMedium eMedium) throws Exception {
		return shelves.revokeLending((Lendable) eMedium);
	}
	

	private boolean existsLendable(EntityManager em, EMediumProperties properties) {
//		String path = properties.getAttribute(EMediumAttribute.PATH);
//		for (Lendable lendable : this)
//			if (lendable.getPath().equals(path))
//				return true;
		
		TypedQuery<Lendable> q = em.createNamedQuery(Lendable.FIND_ALL, Lendable.class);
		
		List<Lendable> list=q.getResultList();
		for(Lendable len : list)
			if((len.getEMediumProperties()).equals(properties))
				return true;
		
		return false;
	}
	
	@Override
	public Iterator<EMedium> iterator() {
//		return JPAUtils.findList(Lendable.FIND_ALL, Lendable.class).iterator();
		TypedQuery<Lendable> q = em.createNamedQuery(Lendable.FIND_ALL, Lendable.class);
		List<Lendable> lLend= q.getResultList();
		List<EMedium> lEM = new LinkedList<EMedium>();
		for(EMedium emed:lLend)
			lEM.add(emed);
		return lEM.iterator();
		
	}
	
	public Lendable getLastAddedLendable() {
		return lastAddedLendable;
	}

	public EMediumProperties readProperties(EMedium eMedium) throws Exception {
				Lendable lendable = (Lendable) em.merge(eMedium);
				em.refresh(lendable);
				return lendable.getEMediumProperties();
	}
 	
}
