package model.shelves;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.EMedium;
//import model.events.ShelfCollectionListener;
import model.lendables.Lendable;
import model.rentals.Rental;
import model.shelves.criteria.Criterion;
import model.shelves.criteria.RecentlyBorrowedCriteria;
import css.AppProperties;

@Stateless
@LocalBean
public class Shelves {

	public static final String MYRENTALS = AppProperties.INSTANCE.RENTALS_SHELF_NAME;
	public static final String RECENTLY_BORROWED = AppProperties.INSTANCE.RECENTLY_BORROWED_SHELF_NAME;
			
//	@EJB private EventListenerList shelfListeners;
	
	@PersistenceContext private EntityManager em;
	
	public Shelves () throws Exception {
	//	this.shelfListeners = new EventListenerList();
		ensureMyRentals();
		addSmartShelf(RECENTLY_BORROWED, new RecentlyBorrowedCriteria(1000 * 60));
	}

	private void ensureMyRentals() throws Exception {
		if (!existsShelf(em, MYRENTALS))
			em.persist(new NormalShelf(MYRENTALS));

	}

	public boolean addNormalShelf(String name) {
		if(existsShelf(em, name)){
		em.persist(new NormalShelf(name));
		return true;}
		return false;
	}
	
	public boolean addSmartShelf (String name, Criterion criteria) throws Exception {
		Shelf myRentals = getShelf(em, MYRENTALS);
		em.persist( new SmartShelf (name, myRentals, criteria));
		return true;

	}

	private boolean existsShelf(EntityManager em, String name) {
		return getShelf(em, name) != null;
	}
	
	private Shelf getShelf(String name) {
		return em.find(Shelf.class, name);
	}
	
	private Shelf getShelf(EntityManager em, String name) {
		return em.find(Shelf.class, name);
	}
	
	public void removeShelf(String name) throws Exception {
		Shelf rem;
		if (( rem = getShelf(name))!= null) 
			em.remove(rem);

	}

	public List<String> getShelves () throws Exception {
		@SuppressWarnings("unchecked")
		TypedQuery<String> get = (TypedQuery<String>) em.createNamedQuery(Shelf.FIND_ALL_BUT_MYRENTALS);
		List<String> lista =get.getResultList();
//		lista.add(MYRENTALS);
		
//		List<Shelf> theShelves = JPAUtils.runInTxWithResult(new BusinessOperationWithResult() {
		
//			@Override
//			public List<Shelf> execute(EntityManager em) throws Exception {
//				return JPAUtils.findList(em, Shelf.FIND_ALL_BUT_MYRENTALS, 
//						Shelf.NAME, MYRENTALS, Shelf.class);
//			}
//		});
//		return theShelves.stream().map(x -> x.getName()).collect(Collectors.toList());
		return lista;
	}

	public boolean addOrRenewRental(EMedium lendable) {
		Rental search;
		if((search = em.find(Rental.class, lendable.getFile()))  != null){
//			search = 
			Lendable len= new Lendable(lendable.getEMediumProperties());
			em.remove(search);
			em.persist(len);

		}
		
		em.persist(new Lendable(lendable.getEMediumProperties()));
		
		return true;
//		try {
//			return JPAUtils.runInTxWithResult(new BusinessOperationWithResult() {
//				@Override
//				public Boolean execute(EntityManager em) throws Exception {
//					Lendable aLendable = em.merge(lendable.getLendable());
//					em.refresh(aLendable);
//					if (aLendable.hasLicensesAvailable()) {
//						aLendable.createLending();
//						getShelf(em, MYRENTALS).addRental(aLendable.makeRental());
//						return true;
//					}
//					return false;
//				}
//			});
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
	}

	public void addRental(String shelfName, EMedium rental) throws Exception {
//		getShelf(em, shelfName).addRental((Rental)em.merge(rental));
		Shelf s = getShelf(shelfName);
		em.detach(s);
		s.addRental(new Rental(new Lendable(rental.getEMediumProperties())));
		em.merge(s);
//		TODO mebe
	}

	public boolean removeRental(String shelfName, EMedium rental) throws Exception {

				Shelf shelf = getShelf(shelfName);
				boolean removed = shelf.removeRental((Rental)rental);
				em.merge(shelf);
				return removed;

	}
	
	public boolean returnRental (String shelfName, EMedium rental) throws Exception {
				
				Shelf shelf = getShelf(em, MYRENTALS);
				return shelf != null ?
					shelf.returnRental((Rental) rental) : false;

	}

    public boolean canBeViewed(EMedium eMedium) throws Exception {
//    	return JPAUtils.runInTxWithResult(new BusinessOperationWithResult() {
//			@Override
//			public Boolean execute(EntityManager em) throws Exception {
				Shelf shelf = getShelf(em, MYRENTALS);
				return shelf.hasNonExpiredRental((Lendable) eMedium.getLendable());
//			}
//		});
    }

	public List<EMedium> getRentals () throws Exception {
		return getShelfRentals(MYRENTALS);
	}

	public List<EMedium> getShelfRentals (String shelfName) throws Exception {
	//	return JPAUtils.runInTxWithResult(new BusinessOperationWithResult() {
		//	@Override
			//public Shelf execute(EntityManager em) throws Exception {
//				return getShelf(em, shelfName).iterator()shelfNa
		
		
		LinkedList<EMedium> lista = new LinkedList<EMedium>(); 
		for (Rental r : getShelf(shelfName))
			lista.add(r);		
		return lista;
		
		
	//	});
	}
/*
	public void addShelfCollectionListener(ShelfCollectionListener listener) {
        shelfListeners.add(ShelfCollectionListener.class, listener);
    }

    public void removeShelfCollectionListener(ShelfCollectionListener listener) {
        shelfListeners.remove(ShelfCollectionListener.class, listener);
    }
    
    private void fireShelfAdded(ShelfCollectionEvent event) {
    	for (ShelfCollectionListener listener : shelfListeners.getListeners(ShelfCollectionListener.class))
    		listener.shelfAdded(event);
    }

    private void fireShelfRemoved(ShelfCollectionEvent event) {
    	for (ShelfCollectionListener listener : shelfListeners.getListeners(ShelfCollectionListener.class))
    		listener.shelfRemoved(event);
    }
*/
	public boolean revokeLending(Lendable lendable) throws Exception {
	//	return JPAUtils.runInTxWithResult(new BusinessOperationWithResult() {
		//	@Override
		//	public Boolean execute(EntityManager em) throws Exception {
				Shelf shelf = getShelf(em, MYRENTALS);
				return shelf != null ?
						shelf.returnRentalForLendable(lendable) : false;
//			}
	//	});
	}

}
