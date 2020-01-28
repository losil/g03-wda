package ch.hslu.swde.wda.persister.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ch.hslu.swde.wda.domain.Customer;
import ch.hslu.swde.wda.domain.Operator;
import ch.hslu.swde.wda.persister.api.CustomerPersister;

public class CustomerJpaPersister implements CustomerPersister {

	@Override
	public Customer add(Customer customer) throws Exception {
		EntityManager em = JpaUtil.createEntityManager();
		em.getTransaction().begin();

		try {
			em.merge(customer);
			em.getTransaction().commit();
		} catch (Exception e) {

			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}

			throw new RuntimeException(e);

		} finally {
			if (em.isOpen()) {
				em.close();
			}
		}

		return customer;
	}

	@Override
	public Customer update(Customer customer) throws Exception {
		EntityManager em = null;
		Customer customerUpdated = null;
		try {

			em = JpaUtil.createEntityManager();

			em.getTransaction().begin();
			customerUpdated = em.merge(customer);
			em.getTransaction().commit();

		} catch (Exception e) {
			// Logeintarg machen
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();

		}
		return customer;
	}

	@Override
	public boolean delete(Customer customer) throws Exception {
		if (findByID(customer.getKundenNummer()) != null) {

			EntityManager em = JpaUtil.createEntityManager();

			try {
				em.getTransaction().begin();
				if (!em.contains(customer)) {
					customer = em.merge(customer);
				}
				em.remove(customer);
				em.getTransaction().commit();
			} catch (Exception e) {
				em.getTransaction().rollback();
				e.printStackTrace();
			} finally {
				if (em.isOpen()) {
					em.close();
				}
			}
			return true;

		}

		return false;
	}

	@Override
	public boolean deleteByID(int kundenNummer) throws Exception {
		Customer customer = findByID(kundenNummer);

		if (customer != null) {

			EntityManager em = JpaUtil.createEntityManager();

			try {
				em.getTransaction().begin();
				if (!em.contains(customer)) {
					customer = em.merge(customer);
				}
				em.remove(customer);
				em.getTransaction().commit();
			} catch (Exception e) {
				em.getTransaction().rollback();
				e.printStackTrace();

			} finally {
				if (em.isOpen()) {
					em.close();
				}
			}
			return true;

		}

		return false;
	}

	@Override
	public Customer findByFirmenName(String firmenName) throws Exception {
		Customer customer = null;
		EntityManager em = JpaUtil.createEntityManager();

		try {
			TypedQuery<Customer> tQry = em.createQuery("SELECT u FROM Customer u WHERE u.firmenname=:firmenname",
					Customer.class);

			tQry.setParameter("firmenname", firmenName);

			customer = tQry.getSingleResult();

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (em.isOpen()) {
				em.close();
			}
		}

		return customer;
	}

	@Override
	public Customer findByID(int kundenNummer) throws Exception {
		EntityManager em = JpaUtil.createEntityManager();

		Customer customer = em.find(Customer.class, kundenNummer);

		em.clear();

		return customer;
	}

	@Override
	public List<Customer> all() throws Exception {
		List<Customer> customer;

		EntityManager em = JpaUtil.createEntityManager();

		TypedQuery<Customer> tQry = em.createQuery("SELECT c FROM Customer c", Customer.class);

		customer = tQry.getResultList();

		em.close();

		return customer != null ? customer : new ArrayList<Customer>();
	}

}
