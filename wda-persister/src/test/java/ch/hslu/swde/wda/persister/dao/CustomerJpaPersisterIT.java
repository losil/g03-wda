package ch.hslu.swde.wda.persister.dao;

import ch.hslu.swde.wda.domain.Customer;
import ch.hslu.swde.wda.persister.api.CustomerPersister;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class CustomerJpaPersisterIT {
	
	private Customer customer0 = new Customer(999990,"Test0","Teststrasse 0","0a",0000,"Rotkreuz","Test0","0000000000","education");
	private Customer customer1 = new Customer(999991,"Test1","Teststrasse 1","1a",0001,"Luzern","Test1","00000000001","enterprise");
	private Customer customer2 = new Customer(999992,"Test2","Teststrasse 2","2a",0002,"Luzern","Test2","0000000002","enterprise");
	private Customer customer3 = new Customer(999993,"Test3","Teststrasse 3","3a",0003,"Sursee","Test 3","0000000003","standard");
	
	private static CustomerPersister persister = new CustomerJpaPersister();



	@AfterEach
	void tearDown() throws Exception {
		if (persister.findByID(customer0.getKundenNummer()) != null) {
			persister.delete(customer0);
		}
		if (persister.findByID(customer1.getKundenNummer()) != null) {
			persister.delete(customer1);
		}
		if (persister.findByID(customer2.getKundenNummer()) != null) {
			persister.delete(customer2);
		}
		if (persister.findByID(customer3.getKundenNummer()) != null) {
			persister.delete(customer3);
		}
	}

	@Test
	void testAdd() throws Exception {

		// Speichern
		Customer customerAdd = persister.add(customer0);

		// ID aus Objekt holen
		int id = customerAdd.getKundenNummer();

		// Objekt anhand ID von DB lesen
		// System.out.println(id);
		Customer customerFromDB = persister.findByID(id);

		assertNotNull(customerFromDB);
	}

	@Test
	void testAddNull() {
		Assertions.assertThrows(RuntimeException.class, () -> {
			persister.add(null);
		});
	}

	@Test
	void testUpdate() throws Exception {
		int kundenNummer = customer0.getKundenNummer();
		persister.add(customer0);


		Customer customerFromDB = persister.findByID(customer0.getKundenNummer());

		assertNotNull(customerFromDB);

		// Passwort anpassen
		String newTelefon = "0410004141";
		customerFromDB.setTelefon(newTelefon);

		// anpassen
		persister.update(customerFromDB);

		// City nach anpassung ausgeben
		Customer customerAfterUpdate = persister.findByID(customer0.getKundenNummer());
		// prüfen

        assertTrue(customerAfterUpdate.getTelefon() == newTelefon);
	}

	@Test
	void testDeleteByCity() throws Exception {
		

		// Speichern
		persister.add(customer0);

		// ID aus Objekt holen
		int id = customer0.getKundenNummer();

		// Objekt anhand ID von DB lesen
		Customer customerFromDB = persister.findByID(id);

		// prüfen
		assertNotNull(customerFromDB);

		// löschen
		persister.delete(customerFromDB);

		// prüfen ob Objekt noch vorhanden
		Customer customerAfterDeletion = persister.findByID(id);

		assertNull(customerAfterDeletion);
	}

	@Test
	void testDeleteByID() throws Exception {
	

		// Speichern
		persister.add(customer0);

		// ID aus Objekt holen
		int id = customer0.getKundenNummer();

		// Objekt anhand ID von DB lesen
		Customer customerFromDB = persister.findByID(id);

		// prüfen
		assertNotNull(customerFromDB);

		// löschen anhand der id
		persister.deleteByID(id);

		// prüfen ob Objekt noch vorhanden
		Customer customerAfterDeletion = persister.findByID(id);

		// prüfen
		assertNull(customerAfterDeletion);
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
		persister.add(customer0);

		// id auslesen
		int id = customer0.getKundenNummer();

		// City aus DB lesen
		Customer customerFromDB = persister.findByID(id);

		// prüfen
		assertNotNull(customerFromDB);
	}

	@Test
	void testFindByUsername() throws Exception {
		persister.add(customer0);
		persister.add(customer1);
		persister.add(customer2);
		persister.add(customer3);


		assertNotNull(persister.findByFirmenName("Test0"));
		assertTrue(persister.findByFirmenName("Test1").getKundenNummer()== customer1.getKundenNummer());

	}

	@Test
	void testAll() throws Exception {
		persister.add(customer0);
		persister.add(customer1);
		persister.add(customer2);
		persister.add(customer3);


		// Alle City ausgeben
		assertTrue(persister.all().size() >= 4);
	}


}
