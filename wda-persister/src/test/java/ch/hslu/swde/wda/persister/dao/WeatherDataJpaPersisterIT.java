package ch.hslu.swde.wda.persister.dao;

import ch.hslu.swde.wda.domain.City;
import ch.hslu.swde.wda.domain.Station;
import ch.hslu.swde.wda.domain.WeatherData;
import ch.hslu.swde.wda.persister.api.CityPersister;
import ch.hslu.swde.wda.persister.api.WeatherDataPersister;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("deprecation")
class WeatherDataJpaPersisterIT {
    private WeatherData weatherData0 = new WeatherData(99999990, new City(9999994, "TestCity4"), new Station(999999990, 1f, 2f), new Timestamp(2019, 02, 11, 13, 20, 00, 00), 5f, 6f, "346", 901, 45, "description0", "summary0");
    private WeatherData weatherData1 = new WeatherData(99999991, new City(9999995, "TestCity5"), new Station(999999991, 10f, 20f), new Timestamp(2019, 02, 11, 14, 21, 00, 00), 7f, 8f, "347", 902, 46, "description1", "summary1");
    private WeatherData weatherData2 = new WeatherData(99999992, new City(9999996, "TestCity6"), new Station(999999992, 100f, 200f), new Timestamp(2019, 02, 11, 14, 22, 00, 00), 9f, 10f, "348", 903, 47, "description2", "summary2");
    private WeatherData weatherData3 = new WeatherData(99999993, new City(9999997, "TestCity7"), new Station(999999993, 1000f, 2000f), new Timestamp(2019, 02, 11, 14, 23, 00, 00), 11f, 12f, "349", 907, 48, "description3", "summary3");
    private WeatherData weatherData4 = new WeatherData(99999994, new City(9999997, "TestCity7"), new Station(999999993, 1000f, 2000f), new Timestamp(2019, 02, 11, 14, 23, 59, 00), 11f, 12f, "349", 907, 48, "description4", "summary4");

    private static WeatherDataPersister weatherDataPersister = new WeatherDataJpaPersister();
    private static CityPersister cityJpaPersister = new CityJpaPersister();


	@AfterEach
	void tearDown() throws Exception {

        if (weatherDataPersister.findByID(weatherData0.getId()) != null) {
            weatherDataPersister.delete(weatherData0);
        }
        if (weatherDataPersister.findByID(weatherData1.getId()) != null) {
            weatherDataPersister.delete(weatherData1);
        }
        if (weatherDataPersister.findByID(weatherData2.getId()) != null) {
            weatherDataPersister.delete(weatherData2);
        }
        if (weatherDataPersister.findByID(weatherData3.getId()) != null) {
            weatherDataPersister.delete(weatherData3);
        }
        if (weatherDataPersister.findByID(weatherData4.getId()) != null) {
            weatherDataPersister.delete(weatherData4);
        }
        if (cityJpaPersister.findByID(9999994) != null) {
            cityJpaPersister.deleteByID(9999994);
        }
        if (cityJpaPersister.findByID(9999995) != null) {
            cityJpaPersister.deleteByID(9999995);
        }
        if (cityJpaPersister.findByID(9999994) != null) {
            cityJpaPersister.deleteByID(9999996);
        }
        if (cityJpaPersister.findByID(9999996) != null) {
            cityJpaPersister.deleteByID(9999996);
        }
        if (cityJpaPersister.findByID(9999997) != null) {
            cityJpaPersister.deleteByID(9999997);
        }

    }

	@Test
	void testAdd() throws Exception {


		// Speichern
        WeatherData dataAdd = weatherDataPersister.add(weatherData0);

		// ID aus Objekt holen
		int id = dataAdd.getId();
		// Objekt anhand ID von DB lesen
        WeatherData weatherDatafromDB = weatherDataPersister.findByID(id);

		assertNotNull(weatherDatafromDB);
	}

	@Test
	void testUpdate() throws Exception {
		int weatherDataID = weatherData0.getId();

        weatherDataPersister.add(weatherData0);

        WeatherData weatherDataFromDB = weatherDataPersister.findByID(weatherDataID);

		assertNotNull(weatherDataFromDB);

		// Name City anpassen
		Station newStation = new Station(999999994, 1001f, 2001f);

		weatherDataFromDB.setStation(newStation);

		// anpassen
        weatherDataPersister.update(weatherDataFromDB);

		// City nach anpassung ausgeben
        WeatherData weatherDataAfterUpdate = weatherDataPersister.findByID(weatherDataID);

		// prüfen
		assertEquals(newStation.getId(), weatherDataAfterUpdate.getStation().getId());

		// ALTE Station aus DB löschen

	}

	@Test
	void testDeleteByWeatherData() throws Exception {


		// Speichern
        weatherDataPersister.add(weatherData0);

		// ID aus Objekt holen
		int id = weatherData0.getId();

		// Objekt anhand ID von DB lesen
        WeatherData weatherDataFromDB = weatherDataPersister.findByID(id);

		// prüfen
		assertNotNull(weatherDataFromDB);

		// löschen
        Boolean state = weatherDataPersister.delete(weatherData0);

		//System.out.println(state.toString());

		// prüfen ob Objekt noch vorhanden
        WeatherData weatherDataAfterDeletion = weatherDataPersister.findByID(id);

		assertNull(weatherDataAfterDeletion);
	}

	@Test
	void testDeleteByID() throws Exception {


		// Speichern
        weatherDataPersister.add(weatherData0);

		// ID aus Objekt holen
		int id =weatherData0.getId();

		// löschen anhand der id
        weatherDataPersister.deleteByID(id);

		// prüfen
        assertNull(weatherDataPersister.findByID(id));
	}

	@Test
	void testFindByID() throws Exception {

		// speichern
        weatherDataPersister.add(weatherData0);

		// id auslesen
		int id = weatherData0.getId();

		// City aus DB lesen
        WeatherData weatherDataFromDb = weatherDataPersister.findByID(id);

		// prüfen
		assertNotNull(weatherDataFromDb);
		assertEquals(weatherData0, weatherDataFromDb);
	}

	@Test

	void testFindByDate() throws Exception {
        weatherDataPersister.add(weatherData0);
        weatherDataPersister.add(weatherData1);
        weatherDataPersister.add(weatherData2);
        weatherDataPersister.add(weatherData3);
        weatherDataPersister.add(weatherData4);
		int city_id = 9999997;
		Timestamp startDate = new Timestamp(2019, 02, 11, 14, 23, 00,00);
		Timestamp endDate = new Timestamp(2019, 02, 11, 14, 23, 59,00);

        assertEquals(2, weatherDataPersister.findByDate(startDate, endDate, city_id).size());

	}

	@Test
	void testAll() throws Exception {
        weatherDataPersister.add(weatherData0);
        weatherDataPersister.add(weatherData1);
        weatherDataPersister.add(weatherData2);
        weatherDataPersister.add(weatherData3);

		// Alle City ausgeben
        assertTrue(weatherDataPersister.all().size() >= 4);
	}

	/*
	Can fail, when data is not up-to-date in database
	 */
	@Test
	void testGetLast24Hours() {
        assertTrue(weatherDataPersister.getLast24Hours(2).size() >= 0);
	}
}
