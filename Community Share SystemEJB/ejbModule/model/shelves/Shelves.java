package model.shelves;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.swing.event.EventListenerList;

import model.EMedium;
import model.events.ShelfCollectionEvent;
import model.events.ShelfCollectionListener;
import model.lendables.Lendable;
import model.rentals.Rental;
import model.shelves.criteria.Criterion;
import model.shelves.criteria.RecentlyBorrowedCriteria;
import persistence.utils.BusinessOperation;
import persistence.utils.BusinessOperationWithResult;
import persistence.utils.JPAUtils;
import css.AppProperties;

public class Shelves {

	public static final String MYRENTALS = AppProperties.INSTANCE.RENTALS_SHELF_NAME;
	public static final String RECENTLY_BORROWED = AppProperties.INSTANCE.RECENTLY_BORROWED_SHELF_NAME;
			
	private EventListenerList shelfListeners;
	
	public Shelves () throws Exception {
		this.shelfListeners = new EventListenerList();
		ensureMyRentals();
		addSmartShelf(RECENTLY_BORROWED, new RecentlyBorrowedCriteria(1000 * 60));
	}

	private void ensureMyRentals() throws Exception {
		JPAUtils.runInTx(new BusinessOperation() {
			@Override
			public void execute(EntityManager em) throws Exception {
				if (!existsShelf(em, MYRENTALS)) {
					NormalShelf shelf = new NormalShelf(MYRENTALS);
					JPAUtils.persist(em, shelf);
				}
			}
		});
	}

	public boolean addNormalShelf(String name) {
		try {
			NormalShelf shelf = JPAUtils.runInTxWithResult(new BusinessOperationWithResult() {
				@Override
				public NormalShelf execute(EntityManager em) throws Exception {
					NormalShelf shelf = null;
					if (!existsShelf(em, name)) {
						shelf = new NormalShelf (name);
						JPAUtils.persist(em, shelf);
					}
					return shelf;
				}
			});
			if (shelf != null) {
				fireShelfAdded(new ShelfCollectionEvent(name));
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean addSmartShelf (String name, Criterion criteria) throws Exception {
		try {
			SmartShelf shelf = JPAUtils.runInTxWithResult(new BusinessOperationWithResult() {
				@Override
				public SmartShelf execute(EntityManager em) throws Exception {
					SmartShelf shelf = null;
					if (!existsShelf(em, name)) {
						Shelf myRentals = getShelf(em, MYRENTALS);
						shelf = new SmartShelf (name, myRentals, criteria);
						JPAUtils.persist(em, shelf);
					}
					return shelf;
				}
			});
			if (shelf != null) {
				fireShelfAdded(new ShelfCollectionEvent(name));
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private boolean existsShelf(EntityManager em, String name) {
		return getShelf(em, name) != null;
	}
	
	private Shelf getShelf(String name) {
		return JPAUtils.findEntity(Shelf.FIND_BY_NAME, Shelf.NAME, name, Shelf.class);
	}
	
	private Shelf getShelf(EntityManager em, String name) {
		return JPAUtils.findEntity(em, Shelf.FIND_BY_NAME, Shelf.NAME, name, Shelf.class);
	}
	
	public void removeShelf(String name) throws Exception {
		try {
			boolean removed = JPAUtils.runInTxWithResult(new BusinessOperationWithResult() {
				@Override
				public Boolean execute(EntityManager em) throws Exception {
					Shelf shelf = JPAUtils.findEntity(em, Shelf.FIND_BY_NAME, 
							Shelf.NAME, name, Shelf.class);
					if (shelf != null) {
						em.remove(shelf);
						return true;
					} else
						return false;
				}
			});
			if (removed) {
				fireShelfRemoved(new ShelfCollectionEvent(name));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Iterable<String> getShelves () throws Exception {
		List<Shelf> theShelves = JPAUtils.runInTxWithResult(new BusinessOperationWithResult() {
			@Override
			public List<Shelf> execute(EntityManager em) throws Exception {
				return JPAUtils.findList(em, Shelf.FIND_ALL_BUT_MYRENTALS, 
						Shelf.NAME, MYRENTALS, Shelf.class);
			}
		});
		return theShelves.stream().map(x -> x.getName()).collect(Collectors.toList());
	}

	public boolean addOrRenewRental(EMedium lendable) {
		try {
			return JPAUtils.runInTxWithResult(new BusinessOperationWithResult() {
				@Override
				public Boolean execute(EntityManager em) throws Exception {
					Lendable aLendable = em.merge(lendable.getLendable());
					em.refresh(aLendable);
					if (aLendable.hasLicensesAvailable()) {
						aLendable.createLending();
						getShelf(em, MYRENTALS).addRental(aLendable.makeRental());
						return true;
					}
					return false;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void addRental(String shelfName, EMedium rental) throws Exception {
		JPAUtils.runInTx(new BusinessOperation() {
			@Override
			public void execute(EntityManager em) throws Exception {
				getShelf(em, shelfName).addRental((Rental)em.merge(rental));
			}
		});
	}

	public boolean removeRental(String shelfName, EMedium rental) throws Exception {
		return JPAUtils.runInTxWithResult(new BusinessOperationWithResult() {
			@Override
			public Boolean execute(EntityManager em) {
				Shelf shelf = getShelf(shelfName);
				boolean removed = shelf.removeRental((Rental)rental);
				em.merge(shelf);
				return removed;
			}
		});
	}
	
	public boolean returnRental (String shelfName, EMedium rental) throws Exception {
		return JPAUtils.runInTxWithResult(new BusinessOperationWithResult() {
			@Override
			public Boolean execute(EntityManager em) throws Exception {
				Shelf shelf = getShelf(em, MYRENTALS);
				return shelf != null ?
					shelf.returnRental((Rental) rental) : false;
			}
		});
	}

    public boolean canBeViewed(EMedium eMedium) throws Exception {
    	return JPAUtils.runInTxWithResult(new BusinessOperationWithResult() {
			@Override
			public Boolean execute(EntityManager em) throws Exception {
				Shelf shelf = getShelf(em, MYRENTALS);
				return shelf.hasNonExpiredRental(eMedium.getLendable());
			}
		});
    }

	public Iterable<EMedium> getRentals () throws Exception {
		return getShelfRentals(MYRENTALS);
	}

	public Iterable<EMedium> getShelfRentals (String shelfName) throws Exception {
		return JPAUtils.runInTxWithResult(new BusinessOperationWithResult() {
			@Override
			public Shelf execute(EntityManager em) throws Exception {
				return getShelf(em, shelfName);
			}
		});
	}
	
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

	public boolean revokeLending(Lendable lendable) throws Exception {
		return JPAUtils.runInTxWithResult(new BusinessOperationWithResult() {
			@Override
			public Boolean execute(EntityManager em) throws Exception {
				Shelf shelf = getShelf(em, MYRENTALS);
				return shelf != null ?
						shelf.returnRentalForLendable(lendable) : false;
			}
		});
	}

}
