package ch.hslu.swde.wda.persister.dao;

import ch.hslu.swde.wda.domain.Operator;
import ch.hslu.swde.wda.persister.api.OperatorPersister;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class OperatorJpaPersister implements OperatorPersister {

	@Override
	public Operator add(Operator operator) throws Exception {
		EntityManager em = JpaUtil.createEntityManager();
		em.getTransaction().begin();

		try {
			em.persist(operator);
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

		return operator;
	}

	@Override
	public Operator update(Operator operator) throws Exception {
		EntityManager em = null;
		Operator operatorUpdated = null;
		try {

			em = JpaUtil.createEntityManager();

			em.getTransaction().begin();
			operatorUpdated = em.merge(operator);
			em.getTransaction().commit();

		} catch (Exception e) {
			// Logeintarg machen
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();

		}
		return operator;
	}

	@Override
	public boolean delete(Operator operator) throws Exception {
        if (findByID(operator.getPersonalnummer()) != null) {

			EntityManager em = JpaUtil.createEntityManager();

			try {
				em.getTransaction().begin();
				if (!em.contains(operator)) {
					operator = em.merge(operator);
				}
				em.remove(operator);
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
	public boolean deleteByID(int personalNummer) throws Exception {
		Operator operator = findByID(personalNummer);

		if (operator != null) {

			EntityManager em = JpaUtil.createEntityManager();

			try {
				em.getTransaction().begin();
				if (!em.contains(operator)) {
					operator = em.merge(operator);
				}
				em.remove(operator);
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
	public Operator findByUsername(String username) throws Exception {
		Operator operator = null;
		EntityManager em = JpaUtil.createEntityManager();

		try {
			TypedQuery<Operator> tQry = em.createQuery("SELECT u FROM Operator u WHERE u.username=:username",
					Operator.class);

			tQry.setParameter("username", username);

			operator = tQry.getSingleResult();

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (em.isOpen()) {
				em.close();
			}
		}

		return operator;

	}

	@Override
	public Operator findByID(int personalNummer) throws Exception {
		EntityManager em = JpaUtil.createEntityManager();

		Operator operator = em.find(Operator.class, personalNummer);

		em.clear();

		return operator;
	}

	@Override
	public List<Operator> all() throws Exception {
		List<Operator> operators;

		EntityManager em = JpaUtil.createEntityManager();

		TypedQuery<Operator> tQry = em.createQuery("SELECT u FROM Operator u", Operator.class);

		operators = tQry.getResultList();

		em.close();

		return operators != null ? operators : new ArrayList<Operator>();

	}

}
