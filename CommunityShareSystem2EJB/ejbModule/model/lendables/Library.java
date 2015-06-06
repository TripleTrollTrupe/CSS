package model.lendables;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.EMedium;
import model.EMediumAttribute;
import model.EMediumProperties;
import model.shelves.Shelves;

@Stateless
@WebService(endpointInterface="model.lendables.LibraryHandlerRemote")
public class Library implements Iterable<EMedium>, LibraryHandlerRemote, LibraryHandlerLocal {
	
	@PersistenceContext 
	private EntityManager em;
	
	private Shelves shelves;
	private Lendable lastAddedLendable;

	public Library () {
		
	}
	
	public Library(Shelves shelves) {
		this.shelves = shelves;
	}

	public boolean addLendable(EMediumProperties properties) {
		try {
			if(!existsLendable(properties)){
				lastAddedLendable = new Lendable(properties);
				em.persist(lastAddedLendable);
			}
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	public boolean revokeLending(EMedium eMedium) throws Exception {
		return shelves.revokeLending((Lendable) eMedium);
	}


	private boolean existsLendable(EMediumProperties properties) {
		String path = properties.getAttribute(EMediumAttribute.PATH);
		for (EMedium lendable : this)
			if (((Lendable) lendable).getPath().equals(path))
				return true;
		return false;
	}

	@Override
	public Iterator<EMedium> iterator() {
		try {
			TypedQuery<Lendable> q = em.createNamedQuery(Lendable.FIND_ALL, Lendable.class);
			List<Lendable> lLend = q.getResultList();
			
			List<EMedium> lEM = new LinkedList<EMedium>();
			for(Lendable emed:lLend)
				lEM.add(emed);
			
			return lEM.iterator();
		} catch (NoResultException e) {
			List<EMedium> list = Collections.emptyList();
			return list.iterator();
		}
	}
	
	@Override
	public List<EMedium> getAllLendables() {
		List<EMedium> list = new LinkedList<EMedium>();
		Iterator<EMedium> iterator = iterator();
		while(iterator.hasNext())
			list.add(iterator.next());
		return list;
	}
	
	@Override
	public EMedium getLendable(int id) {
		try {
			TypedQuery<Lendable> query = em.createNamedQuery(Lendable.FIND_BY_ID, Lendable.class);
			query.setParameter(Lendable.ID, id);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
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
