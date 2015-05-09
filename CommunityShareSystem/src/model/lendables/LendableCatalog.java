package model.lendables;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import model.EMediumPropertiesData;
import model.EMediumType;

import org.omg.CORBA.portable.ApplicationException;

public class LendableCatalog {

	/**
	 * Entity manager factory for accessing the persistence service 
	 */
	private EntityManagerFactory emf;
	private LendableFactory lf;
	
	/**
	 * Constructs a discount's catalog giving a entity manager factory
	 */
	public LendableCatalog(EntityManagerFactory emf, LendableFactory lf) {
		this.emf = emf;
		this.lf = lf;
	}
	
	/**
	 * Finds a customer given its VAT number.
	 * 
	 * @param vat The VAT number of the customer to fetch from the repository
	 * @return The Customer object corresponding to the customer with the vat number.
	 * @throws ApplicationException When the customer with the vat number is not found.
	 */
	public Lendable getLendable (int id) throws ApplicationException {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Lendable> query = em.createNamedQuery(Lendable.FIND_BY_ID, Lendable.class);
		query.setParameter(Lendable.ID_NUMBER, id);
		try {
			return query.getSingleResult();
		} catch (PersistenceException e) {
			throw new ApplicationException ("Lendable not found.", null);
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
	public void addLendable (EMediumType type,EMediumPropertiesData properties) 
			throws Exception {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(lf.createLendable(type,properties));
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
