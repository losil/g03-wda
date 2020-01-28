package ch.hslu.swde.wda.persister.dao;

import ch.hslu.swde.wda.domain.City;
import ch.hslu.swde.wda.persister.api.CityPersister;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class CityJpaPersisterIT {
	
	private City city0 = new City(9999990, "TestCity0");
	private City city1 = new City(9999991, "TestCity1");
	private City city2 = new City(9999992, "TestCity2");
	private City city3 = new City(9999993, "TestCity3");

	private static CityPersister persister = new CityJpaPersister();


	@AfterEach
	void AfterEach() throws Exception {
		if (persister.findByID(city0.getId()) != null) {
			persister.delete(city0);
		}
		if (persister.findByID(city1.getId()) != null) {
			persister.delete(city1);
		}
		if (persister.findByID(city2.getId()) != null) {
			persister.delete(city2);
		}
		if (persister.findByID(city3.getId()) != null) {
			persister.delete(city3);
		}
		
	}

	@Test
	void testAdd() throws Exception {

		// Speichern
		City cityadd = persister.add(city0);

		// ID aus Objekt holen
		int id = cityadd.getId();

		// Objekt anhand ID von DB lesen
		// System.out.println(id);
		City cityfromDB = persister.findByID(id);

		assertNotNull(cityfromDB);
	}

	@Test
	void testAddNull() {
		Assertions.assertThrows(RuntimeException.class, () -> {
			persister.add(null);
		});
	}

	@Test
	void testUpdate() throws Exception {
		int cityID = city0.getId();

		persister.add(city0);

		City cityFromDb = persister.findByID(city0.getId());

		assertNotNull(cityFromDb);

		// Name der City anpassen
		String newCityName = "newName";
		cityFromDb.setName(newCityName);

		// anpassen
		persister.update(cityFromDb);

		// City nach anpassung ausgeben
        City cityAfterUpdate = persister.findByID(city0.getId());

		// prüfen
        assertTrue(cityAfterUpdate.getName() == newCityName);
	}

	@Test
	void testDeleteByCity() throws Exception {


		// Speichern
		persister.add(city0);

		// ID aus Objekt holen
		int id = city0.getId();

		// Objekt anhand ID von DB lesen
		City cityfromDB = persister.findByID(id);

		// prüfen
		assertNotNull(cityfromDB);

		// löschen
		persister.delete(cityfromDB);

		// prüfen ob Objekt noch vorhanden
		City cityAfterDeletion = persister.findByID(id);

		assertNull(cityAfterDeletion);
	}

	@Test
	void testDeleteByID() throws Exception {

		// Speichern
		persister.add(city0);

		// ID aus Objekt holen
		int id = city0.getId();

		// Objekt anhand ID von DB lesen
		City cityFromDb = persister.findByID(id);

		// prüfen
		assertNotNull(cityFromDb);

		// löschen anhand der id
		persister.deleteByID(id);

		// prüfen ob Objekt noch vorhanden
		City cityAfterDeletion = persister.findByID(id);

		// prüfen
		assertNull(cityAfterDeletion);
	}

	@Test
	void testDeleteNull() {
		Assertions.assertThrows(RuntimeException.class, () -> {
			persister.add(null);
		});
	}

	@Test
	void testFindByID() throws Exception {

		// speichern
		persister.add(city0);

		// id auslesen
		int id = city0.getId();

		// City aus DB lesen
		City cityFromDb = persister.findByID(id);

		// prüfen
		assertNotNull(cityFromDb);
	}

	@Test
	void testFindByName() throws Exception {
		persister.add(city0);
		persister.add(city1);
		persister.add(city2);
		persister.add(city3);

		assertNotNull(persister.findByName("TestCity0"));
		assertTrue(persister.findByName("TestCity1").getId() == 9999991);
	}

	@Test
	void testAll() throws Exception {
		persister.add(city0);
		persister.add(city1);
		persister.add(city2);
		persister.add(city3);


		// Alle City ausgeben
		assertTrue(persister.all().size() >= 4);
	}

}
