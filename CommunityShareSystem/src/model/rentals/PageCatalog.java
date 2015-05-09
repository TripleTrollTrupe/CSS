package model.rentals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.swing.event.EventListenerList;

import org.omg.CORBA.portable.ApplicationException;

public class PageCatalog {

	/**
	 * Entity manager factory for accessing the persistence service 
	 */
	private EntityManagerFactory emf;
	private PageFactory pf;
	
	/**
	 * Constructs a discount's catalog giving a entity manager factory
	 */
	public PageCatalog(EntityManagerFactory emf, PageFactory pf) {
		this.emf = emf;
		this.pf = pf;
	}
	
	/**
	 * Finds a customer given its VAT number.
	 * 
	 * @param vat The VAT number of the customer to fetch from the repository
	 * @return The Customer object corresponding to the customer with the vat number.
	 * @throws ApplicationException When the customer with the vat number is not found.
	 */
	public Page getPage (int id) throws ApplicationException {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Page> query = em.createNamedQuery(Page.FIND_BY_ID, Page.class);
		query.setParameter(Page.ID_NUMBER, id);
		try {
			return query.getSingleResult();
		} catch (PersistenceException e) {
			throw new ApplicationException ("Lendable not found.", null);
		} finally {
			em.close();
		}
	}
	
	
	public void addPage () 
			throws Exception {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(pf.createPage());
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			throw new Exception("Error adding Lendable", e);
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
	public void addPage (int pageNum, EventListenerList listeners) 
			throws Exception {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(pf.createPage(pageNum, listeners));
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			throw new Exception("Error adding Lendable", e);
		} finally {
			em.close();
		}
	}
}
