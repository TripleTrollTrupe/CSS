package model.rentals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import model.EMedium;

import org.omg.CORBA.portable.ApplicationException;

public class RentalCatalog {

	/**
	 * Entity manager factory for accessing the persistence service 
	 */
	private EntityManagerFactory emf;
	private RentalFactory rf;
	
	/**
	 * Constructs a discount's catalog giving a entity manager factory
	 */
	public RentalCatalog(EntityManagerFactory emf, RentalFactory rf) {
		this.emf = emf;
		this.rf = rf;
	}
	
	/**
	 * Finds a customer given its VAT number.
	 * 
	 * @param vat The VAT number of the customer to fetch from the repository
	 * @return The Customer object corresponding to the customer with the vat number.
	 * @throws ApplicationException When the customer with the vat number is not found.
	 */
	public Rental getRental (int id) throws ApplicationException {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Rental> query = em.createNamedQuery(Rental.FIND_BY_ID, Rental.class);
		query.setParameter(Page.ID_NUMBER, id);
		try {
			return query.getSingleResult();
		} catch (PersistenceException e) {
			throw new ApplicationException ("Rental not found.", null);
		} finally {
			em.close();
		}
	}
	
	
	public void addRental (EMedium rental) 
			throws Exception {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(rf.createRental(rental));
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			throw new Exception("Error adding Rental", e);
		} finally {
			em.close();
		}
	}
	
	/**
	 * Adds a new customer
	 * 
	 * @param vat The customer's VAT number
	 * @param designation The customer's designation
	 * @param phoneNumber The customer's phone number
	 * @param discountType The customer's discount type
	 * @throws Exception 
	 */
	public void addBookRental () 
			throws Exception {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(rf.createBookRental());
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			throw new Exception("Error adding BookRental", e);
		} finally {
			em.close();
		}
	}
}
