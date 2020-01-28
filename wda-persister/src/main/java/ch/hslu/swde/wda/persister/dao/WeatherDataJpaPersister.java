package ch.hslu.swde.wda.persister.dao;

import ch.hslu.swde.wda.domain.City;
import ch.hslu.swde.wda.domain.WeatherData;
import ch.hslu.swde.wda.persister.api.WeatherDataPersister;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class WeatherDataJpaPersister implements WeatherDataPersister {

	@Override
	public WeatherData add(WeatherData weatherData) {
		EntityManager em = null;
		try {

			em = JpaUtil.createEntityManager();

			em.getTransaction().begin();
			em.merge(weatherData.getCity());
			em.merge(weatherData.getStation());
			em.merge(weatherData);
			em.getTransaction().commit();

		} catch (Exception e) {
			// Logeintarg machen
			e.printStackTrace();
            if (em != null) {
                em.getTransaction().rollback();
            }
		} finally {
			em.close();

		}
		return weatherData;
	}

	@Override
	public WeatherData update(WeatherData weatherData) {
		EntityManager em = null;
		WeatherData weatherDataUpdated = null;
		try {

			em = JpaUtil.createEntityManager();

			em.getTransaction().begin();
			em.merge(weatherData);
			em.getTransaction().commit();

		} catch (Exception e) {
			// Logeintarg machen
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();

		}
		return weatherData;
	}

	@Override
	public boolean delete(WeatherData weatherData) throws Exception {
		if (findByID(weatherData.getId()) != null) {

			EntityManager em = JpaUtil.createEntityManager();

			try {
				em.getTransaction().begin();
				if (!em.contains(weatherData)) {
					weatherData = em.merge(weatherData);
				}
				// nicht sicher ob wirklich gelöscht werden muss
				// em.remove(weatherData.getCity());
				// em.remove(weatherData.getStation());
				em.remove(weatherData);
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
	public boolean deleteByID(int data_id) throws Exception {
		WeatherData weatherData = findByID(data_id);

		if (weatherData != null) {

			EntityManager em = JpaUtil.createEntityManager();

			try {
				em.getTransaction().begin();
				if (!em.contains(weatherData)) {
					weatherData = em.merge(weatherData);
				}
				// nicht sicher ob wirklich gelöscht werden muss
				// em.remove(weatherData.getCity());
				// em.remove(weatherData.getStation());
				em.remove(weatherData);
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
	public WeatherData findByID(int data_id) {
		EntityManager em = JpaUtil.createEntityManager();

		WeatherData weatherData = em.find(WeatherData.class, data_id);

		em.clear();

		return weatherData;
	}

	@Override
	/* Noch in Testphase! */
	public List<WeatherData> findByDate(Timestamp startDate, Timestamp endDate, int city_id) {

		List<WeatherData> data = null;

		EntityManager em = JpaUtil.createEntityManager();
		City city = null;
		try {
			city = new CityJpaPersister().findByID(city_id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			TypedQuery<WeatherData> tQry = em.createQuery(
					"SELECT w FROM WeatherData w WHERE w.timestamp >= :startDate AND w.timestamp <= :endDate AND w.city = :city_id",WeatherData.class);
			tQry.setParameter("city_id", city);
			tQry.setParameter("startDate", startDate);
			tQry.setParameter("endDate", endDate);

			data = tQry.getResultList();

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (em.isOpen()) {
				em.close();
			}
		}

		return data != null ? data : new ArrayList<WeatherData>();
	}

	@Override
	public List<WeatherData> all() {
		List<WeatherData> weatherData;

		EntityManager em = JpaUtil.createEntityManager();

		TypedQuery<WeatherData> tQry = em.createQuery("SELECT w FROM WeatherData w", WeatherData.class);

		weatherData = tQry.getResultList();

		em.close();

		return weatherData != null ? weatherData : new ArrayList<WeatherData>();
	}

	@Override
	public List<WeatherData> getLast24Hours(final int city_id) {
		List<WeatherData> weatherData = null;

		EntityManager em = JpaUtil.createEntityManager();
		City city = null;
		try {
			city = new CityJpaPersister().findByID(city_id);
		} catch (Exception e) {
			e.printStackTrace();
		}

        TypedQuery<WeatherData> typedQuery = em.createQuery("SELECT w FROM WeatherData w " +
               "WHERE w.city = :city_id " +
		        "AND w.timestamp >= :yesterday", WeatherData.class);
		typedQuery.setParameter("city_id", city);
        typedQuery.setParameter("yesterday", Timestamp.valueOf(ZonedDateTime.now().toLocalDateTime().minusDays(1)));

		weatherData = typedQuery.getResultList();

		return weatherData != null ? weatherData : new ArrayList<WeatherData>();
	}


}
