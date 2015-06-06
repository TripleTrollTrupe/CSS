package model.shelves;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.EMedium;
//import model.events.ShelfCollectionListener;
import model.lendables.Lendable;
import model.rentals.Rental;
import model.shelves.criteria.Criterion;
import model.shelves.criteria.RecentlyBorrowedCriteria;
import css.AppPropertiesServer;

@Stateless
@WebService(endpointInterface="model.shelves.ShelvesHandlerRemote")
public class Shelves implements ShelvesHandlerRemote, ShelvesHandlerLocal {

	private String MYRENTALS;
	private String RECENTLY_BORROWED;

	@EJB
	private AppPropertiesServer app;
	
	@PersistenceContext 
	private EntityManager em;

	public Shelves () {

	}

	@PostConstruct
	public void init() {
		try {
			MYRENTALS = app.RENTALS_SHELF_NAME;
			RECENTLY_BORROWED = app.RECENTLY_BORROWED_SHELF_NAME;
			ensureMyRentals();
			addSmartShelf(RECENTLY_BORROWED, new RecentlyBorrowedCriteria(1000 * 60));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void ensureMyRentals() throws Exception {
		if (!existsShelf(MYRENTALS)) {
			NormalShelf shelf = new NormalShelf(MYRENTALS);
			em.persist(shelf);
		}
	}

	public boolean addNormalShelf(String name) {
		NormalShelf shelf = null;
		if (!existsShelf(name)) {
			shelf = new NormalShelf (name);
			em.persist(shelf);
		}

		if (shelf != null)
			return true;

		return false;
	}

	public boolean addSmartShelf (String name, Criterion criteria) throws Exception {
		SmartShelf shelf = null;
		if (!existsShelf(name)) {
			Shelf myRentals = getShelf(MYRENTALS);
			shelf = new SmartShelf (name, myRentals, criteria);
			em.persist(shelf);
		}
		
		if (shelf != null)
			return true;

		return false;
	}

	private boolean existsShelf(String name) {
		return getShelf(name) != null;
	}

	private Shelf getShelf(String name) {
		try {
			TypedQuery <Shelf> query = em.createNamedQuery(Shelf.FIND_BY_NAME,Shelf.class);
			query.setParameter(Shelf.NAME, name);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}


	public void removeShelf(String name) throws Exception {
		Shelf shelf = getShelf(name);
		if (shelf != null) 
			em.remove(shelf);										
	}

	public List<String> getShelves () throws Exception {
		try {
			TypedQuery <Shelf> query = em.createNamedQuery(Shelf.FIND_ALL_BUT_MYRENTALS,Shelf.class);
			query.setParameter(Shelf.NAME, MYRENTALS);
			List<Shelf> theShelves = query.getResultList();	
			
			return theShelves.stream().map(x -> x.getName()).collect(Collectors.toList());
		} catch (NoResultException e) {
			return Collections.emptyList();
		}
	}

	public boolean addOrRenewRental(EMedium lendable) {
		Lendable aLendable = (Lendable) em.merge(lendable.getLendable());
		em.refresh(aLendable);
		if (aLendable.hasLicensesAvailable()) {
			aLendable.createLending();
			getShelf(MYRENTALS).addRental(aLendable.makeRental());
			return true;
		}
		return false;
	}

	public void addRental(String shelfName, EMedium rental) throws Exception {
		getShelf(shelfName).addRental((Rental)em.merge(rental));			
	}

	public boolean removeRental(String shelfName, EMedium rental) throws Exception {
		Shelf shelf = getShelf(shelfName);
		boolean removed = shelf.removeRental((Rental)rental);
		em.merge(shelf);
		return removed;
	}

	public boolean returnRental (String shelfName, EMedium rental) throws Exception {
		Shelf shelf = getShelf(MYRENTALS);
		return shelf != null ? shelf.returnRental((Rental) rental) : false;
	}

	public boolean canBeViewed(EMedium eMedium) throws Exception {
		Shelf shelf = getShelf( MYRENTALS);
		return shelf.hasNonExpiredRental((Lendable) eMedium.getLendable());			
	}

	public List<EMedium> getRentals () throws Exception {
		return getShelfRentals(MYRENTALS);
	}

	public List<EMedium> getShelfRentals (String shelfName) throws Exception {
		List<EMedium> list = new ArrayList<>();
		for(Rental r : getShelf(shelfName))
			list.add(r);
		return list;
	}

	public boolean revokeLending(EMedium lendable) throws Exception {
		Shelf shelf = getShelf(MYRENTALS);
		return shelf != null ? shelf.returnRentalForLendable((Lendable) lendable) : false;

	}

}
