package model.rentals;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.EMedium;
import model.events.EMediumEvent;
//import model.events.EMediumListener;

public class AnnotationsFacade {

//	private EventListenerList eMediumListeners = new EventListenerList();
	
	@PersistenceContext private EntityManager em;
		
	public BookRental toggleBookmark(BookRental rental, int pageNum) throws Exception {
//		BookRental book = JPAUtils.runInTxWithResult(new BusinessOperationWithResult() {
//			@Override
//			public BookRental execute(EntityManager em) throws Exception {
//		em.detach(rental);
				BookRental book = em.merge(rental);
				em.refresh(book);
				book.toggleBookmark(pageNum);
				return book;
//			}
//		});
//		fireBookmarkToggled(new EMediumEvent (book, pageNum, book.isBookmarked(pageNum)));
//		return book;
	}

	public EMedium addAnnotation(Rental rental, String text) throws Exception {
//		EMediumEvent event = JPAUtils.runInTxWithResult(new BusinessOperationWithResult() {
//			@Override
//			public EMediumEvent execute(EntityManager em) throws Exception {
				Rental aRental = em.merge((Rental) rental);
				em.refresh(aRental);
				int annotationNum = aRental.addAnnotation(text);
				boolean hasAnnotations = aRental.hasAnnotations();
				return (new EMediumEvent(aRental, 0, annotationNum, hasAnnotations, text)).getEMedium();
				
//			}
//		});
//		fireAnnotationAdded(event);
//		return event.getEMedium();
	}
	
	public EMedium removeAnnotation(Rental rental, int annotationNum) throws Exception {
//		EMediumEvent event = JPAUtils.runInTxWithResult(new BusinessOperationWithResult() {
//			@Override
//			public EMediumEvent execute(EntityManager em) throws Exception {
				Rental aRental = em.merge(rental);
				em.refresh(aRental);
				aRental.removeAnnotation(annotationNum);
				boolean hasAnnotations = aRental.hasAnnotations();
				return new EMediumEvent(aRental, 0, annotationNum, hasAnnotations).getEMedium();
//			}
//		});
//		fireAnnotationRemoved(event);
//		return event.getEMedium();
	}
	
	public EMedium addAnnotation(BookRental rental, int pageNum, String text) throws Exception {
//		EMediumEvent event = JPAUtils.runInTxWithResult(new BusinessOperationWithResult() {
//			@Override
//			public EMediumEvent execute(EntityManager em) throws Exception {
				BookRental book = em.merge((BookRental) rental);
				em.refresh(book);
				int annotationNum = book.addAnnotation(pageNum, text);
				boolean hasAnnotations = book.hasAnnotations(pageNum);
				return (new EMediumEvent(book, pageNum, annotationNum, hasAnnotations, text)).getEMedium();
//			}
//		});
////		fireAnnotationAdded(event);
//		return event.getEMedium();
	}

	public EMedium removeAnnotation(BookRental rental, int pageNum, int annotationNum) throws Exception {
//		EMediumEvent event = JPAUtils.runInTxWithResult(new BusinessOperationWithResult() {
//			@Override
//			public EMediumEvent execute(EntityManager em) throws Exception {
				BookRental book = em.merge((BookRental) rental);
				em.refresh(book);
				book.removeAnnotation(pageNum, annotationNum);
				boolean hasAnnotations = book.hasAnnotations(pageNum);
				return (new EMediumEvent(book, pageNum, annotationNum, hasAnnotations)).getEMedium();
	}
//		});
//		//fireAnnotationRemoved(event);
//		return event.getEMedium();
//	}
/*
	private void fireBookmarkToggled(EMediumEvent event) {
	   	for (EMediumListener listener : eMediumListeners.getListeners(EMediumListener.class))
	   		listener.bookmarkToggled(event);
	}
	
	private void fireAnnotationAdded(EMediumEvent event) {
	  	for (EMediumListener listener : eMediumListeners.getListeners(EMediumListener.class))
	   		listener.annotationAdded(event);
	}
	
	private void fireAnnotationRemoved(EMediumEvent event) {
		for (EMediumListener listener : eMediumListeners.getListeners(EMediumListener.class))
			listener.annotationRemoved(event);
	}
	
	public void addEMediumListener(EMediumListener listener) {
		eMediumListeners.add(EMediumListener.class, listener);
	}
	
	public void removeEMediumListener(EMediumListener listener) {
		eMediumListeners.remove(EMediumListener.class, listener);
	}
	*/
}
