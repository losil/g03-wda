package ch.hslu.swde.wda.persister.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ch.hslu.swde.wda.domain.City;
import ch.hslu.swde.wda.persister.api.CityPersister;

public class CityJpaPersister implements CityPersister {

	@Override
	public City add(City city) throws Exception {
		EntityManager em = JpaUtil.createEntityManager();
		em.getTransaction().begin();

		try {
			em.merge(city);
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

		return city;

	}

	@Override
	public City update(City city) throws Exception {
		EntityManager em = null;
		City cityUpdated = null;
		try {

			em = JpaUtil.createEntityManager();

			em.getTransaction().begin();
			cityUpdated = em.merge(city);
			em.getTransaction().commit();

		} catch (Exception e) {
			// Logeintarg machen
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();

		}
		return city;
	}

	@Override
	public boolean delete(City city) throws Exception {

		if (findByID(city.getId()) != null) {

			EntityManager em = JpaUtil.createEntityManager();

			try {
				em.getTransaction().begin();
				if (!em.contains(city)) {
					city = em.merge(city);
				}
				em.remove(city);
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
	public boolean deleteByID(int city_ID) throws Exception {
		City city = findByID(city_ID);

		if (city != null) {

			EntityManager em = JpaUtil.createEntityManager();

			try {
				em.getTransaction().begin();
				if (!em.contains(city)) {
					city = em.merge(city);
				}
				em.remove(city);
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
	public City findByName(String name) {
		City city = null;
		EntityManager em = JpaUtil.createEntityManager();

		try {
			TypedQuery<City> tQry = em.createQuery("SELECT c FROM City c WHERE c.name=:name", City.class);

			tQry.setParameter("name", name);

			city = tQry.getSingleResult();

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (em.isOpen()) {
				em.close();
			}
		}

		return city;

	}

	@Override
	public City findByID(int city_ID) throws Exception {
		EntityManager em = JpaUtil.createEntityManager();

		City city = em.find(City.class, city_ID);

		em.clear();

		return city;
	}

	@Override
	public List<City> all() throws Exception {
		List<City> cities;

		EntityManager em = JpaUtil.createEntityManager();

		TypedQuery<City> tQry = em.createQuery("SELECT c FROM City c", City.class);

		cities = tQry.getResultList();

		em.close();

		return cities != null ? cities : new ArrayList<City>();

	}
}
