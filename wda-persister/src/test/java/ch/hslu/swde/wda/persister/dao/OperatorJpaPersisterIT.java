package ch.hslu.swde.wda.persister.dao;

import ch.hslu.swde.wda.domain.Operator;
import ch.hslu.swde.wda.persister.api.OperatorPersister;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperatorJpaPersisterIT {
	private static OperatorPersister persister = new OperatorJpaPersister();
	
	private Operator operator0 = new Operator(9999990, "Test0", "Test0", "Test0", "password0");
	private Operator operator1 = new Operator(9999991, "Test1", "Test1", "Test1", "password1");
	private Operator operator2 = new Operator(9999992, "Test2", "Test2", "Test2", "password2");
	private Operator operator3 = new Operator(9999993, "Test3", "Test3", "Test3", "password3");
	


	@AfterEach
	void afterEach() throws Exception {
		if (persister.findByID(operator0.getPersonalnummer()) != null) {
			persister.delete(operator0);
		}
		if (persister.findByID(operator1.getPersonalnummer()) != null) {
			persister.delete(operator1);
		}
		if (persister.findByID(operator2.getPersonalnummer()) != null) {
			persister.delete(operator2);
		}
		if (persister.findByID(operator3.getPersonalnummer()) != null) {
			persister.delete(operator3);
		}
	}

	@Test
	void testAdd() throws Exception {

		// Speichern
		Operator operatorAdd = persister.add(operator0);

		// ID aus Objekt holen
		int id = operatorAdd.getPersonalnummer();

		// Objekt anhand ID von DB lesen
		// System.out.println(id);
		Operator operatorFromDB = persister.findByID(id);

		assertNotNull(operatorFromDB);
	}

	@Test
	void testAddNull() {
		Assertions.assertThrows(RuntimeException.class, () -> {
			persister.add(null);
		});
	}

	@Test
	void testUpdate() throws Exception {
		int personamNummer = operator0.getPersonalnummer();

		persister.add(operator0);

		Operator operatorFromDb = persister.findByID(operator0.getPersonalnummer());

		assertNotNull(operatorFromDb);

		// Passwort anpassen
		String newPassort = "test_password4";
		operatorFromDb.setPasswort(newPassort);

		// anpassen
		persister.update(operatorFromDb);

		// City nach anpassung ausgeben
		Operator operatorAfterUpdate = persister.findByID(operator0.getPersonalnummer());
		// prüfen
		assertTrue(operatorAfterUpdate.getPasswort() == newPassort);
	}

	@Test
	void testDeleteByCity() throws Exception {


		// Speichern
		persister.add(operator0);

		// ID aus Objekt holen
		int id = operator0.getPersonalnummer();

		// Objekt anhand ID von DB lesen
		Operator operatorfromDB = persister.findByID(id);

		// prüfen
		assertNotNull(operatorfromDB);

		// löschen
		persister.delete(operatorfromDB);

		// prüfen ob Objekt noch vorhanden
		Operator operatorAfterDeletion = persister.findByID(id);

		assertNull(operatorAfterDeletion);
	}

	@Test
	void testDeleteByID() throws Exception {


		// Speichern
		persister.add(operator0);

		// ID aus Objekt holen
		int id = operator0.getPersonalnummer();

		// Objekt anhand ID von DB lesen
		Operator operatorFromDB = persister.findByID(id);

		// prüfen
		assertNotNull(operatorFromDB);

		// löschen anhand der id
		persister.deleteByID(id);

		// prüfen ob Objekt noch vorhanden
		Operator operatorAfterDeletion = persister.findByID(id);

		// prüfen
		assertNull(operatorAfterDeletion);
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
		persister.add(operator0);

		// id auslesen
		int id = operator0.getPersonalnummer();

		// City aus DB lesen
		Operator operatorFromDB = persister.findByID(id);

		// prüfen
		assertNotNull(operatorFromDB);
	}

	@Test
	void testFindByUsername() throws Exception {
		persister.add(operator0);
		persister.add(operator1);
		persister.add(operator2);
		persister.add(operator3);
		assertNotNull(persister.findByUsername("Test1"));
		assertTrue(persister.findByUsername("Test0").getPersonalnummer() == 9999990);
	}

	@Test
	void testAll() throws Exception {
		persister.add(operator0);
		persister.add(operator1);
		persister.add(operator2);
		persister.add(operator3);

		// Alle City ausgeben
		assertTrue(persister.all().size() >= 4);
	}

}
