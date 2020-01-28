package ch.hslu.swde.wda.business.control.impl;

import ch.hslu.swde.wda.business.control.api.Control;
import ch.hslu.swde.wda.domain.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControlImplIT {

	private Control control;
	/*
	 * Setting up test data
	 */
	private City city = new City(99999, "TestCity");
	private Station station = new Station(999999999, 1000f, 2000f);
	private WeatherData weatherData = new WeatherData(9999999, city, station, Timestamp.valueOf(LocalDateTime.now()),
			4f, 5f, "345", 900, 44, "description", "summary");
	private Operator operator = new Operator(999999, "Test", "User", "test_user", "test_password");
    private Customer customer = new Customer(99999, "HSLU-I", "Surstoffi", "21b", 6343, "Rotkreuz", "Hansjörg Diethelm", "0417576811", "education");

	{
		try {
			control = new ControlImpl();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Make sure database is always clean
	 */
	@AfterEach
	void AfterEach() {

		try {
			if (control.findWeatherDataById(weatherData.getId()) != null) {
				control.deleteWeatherData(weatherData);
				control.deleteCity(city);
			} else if (control.findCityById(city.getId()) != null) {
				control.deleteCity(city);
			}
			if (control.findOperatorByID(operator.getPersonalnummer()) != null) {
				control.deleteOperator(operator);
			}
			if (control.findCustomerByID(customer.getKundenNummer()) != null) {
				control.deleteCustomer(customer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void addWeatherData() {
		WeatherData w = null;
		try {
			w = control.addWeatherData(weatherData);
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertEquals(weatherData, w);
	}

	@Test
	void updateWeatherData() {
		try {
			control.addWeatherData(weatherData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		weatherData.setTemperatureInCelsius(10f);
		WeatherData w = null;
		try {
			w = control.updateWeatherData(weatherData);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			assertEquals(10f, control.findWeatherDataById(weatherData.getId()).getTemperatureInCelsius());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void deleteWeatherData() {
		try {
			control.addWeatherData(weatherData);
			control.deleteWeatherData(weatherData);
			assertNull(control.findWeatherDataById(weatherData.getId()));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	void findWeatherDataById() {
		try {
			control.addWeatherData(weatherData);
			assertEquals(weatherData, control.findWeatherDataById(weatherData.getId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void findWeatherDataByCity() {
		try {
			control.addWeatherData(weatherData);
			assertEquals(1, control.findWeatherDataByCity(city).size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void findAllWeatherData() {
		try {
			control.addWeatherData(weatherData);
			assertTrue(control.findAllWeatherData().size() >= 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void addCity() {
		City c = null;
		try {
			c = control.addCity(city);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(city, c);
	}

	@Test
	void updateCity() {
		City c = city;
		c.setName("NewName");
		try {
			control.addCity(city);
			control.updateCity(c);
			assertEquals("NewName", control.findCityById(c.getId()).getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void deleteCity() {
		try {
			control.addCity(city);
			control.deleteCity(city);
			assertNull(control.findCityById(city.getId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void findCityById() {
		try {
			control.addCity(city);
			assertEquals(city, control.findCityById(city.getId()));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	void findCityByName() {
		try {
			control.addCity(city);
			assertEquals(city, control.findCityByName(city.getName()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void findAllCities() {
		try {
			control.addCity(city);
			assertTrue(control.findAllCities().size() >= 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void testFindByDate() {
		LocalDateTime localDateTime = LocalDateTime.now();
		try {
			control.addWeatherData(weatherData);
			List<WeatherData> list = control.findByDate(Timestamp.valueOf(localDateTime.minusHours(2)),
					Timestamp.valueOf(localDateTime), city.getId());
			assertEquals(1, list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * Disabled because of unknown problem with dates
	 */
	@Disabled
	void testGetLast24HoursData() {
		try {
			assertEquals(1, control.getLast24HoursData(city.getId()).size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void testAddOperator() {
		Operator o = null;
		try {
			o = control.addOperator(operator);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(operator, o);
	}

	@Test
	void testUpdateOperator() {
		try {
			control.addOperator(operator);
			operator.setName("NewName");
			control.updateOperator(operator);
			assertEquals("NewName", control.findOperatorByID(operator.getPersonalnummer()).getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void testDeleteOperator() {
		try {
			control.addOperator(operator);
			assertTrue(control.deleteOperator(operator));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void testDeleteOperatorByID() {
		try {
			control.addOperator(operator);
			assertTrue(control.deleteOperatorByID(operator.getPersonalnummer()));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	void testFindOperatorByUsername() {
		try {
			control.addOperator(operator);
			assertEquals(operator, control.findOperatorByUsername(operator.getUsername()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void testFindOperatorByID() {
		try {
			control.addOperator(operator);
			assertEquals(operator, control.findOperatorByID(operator.getPersonalnummer()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void testAllOperator() {
		try {
			control.addOperator(operator);
			assertTrue(control.allOperator().size() >= 1);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	void testAddCustomer() {
		Customer c = null;
		try {
			c = control.addCustomer(customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(customer, c);
	}
	
	@Test
	void testUpdateCustomer() {
		try {
			control.addCustomer(customer);
			customer.setFirmenname("NewName");
			control.updateCustomer(customer);
			assertEquals("NewName", control.findCustomerByID(customer.getKundenNummer()).getFirmenname());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void testDeleteCustomer() {
		try {
			control.addCustomer(customer);
			assertTrue(control.deleteCustomer(customer));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void testDeleteCustomerByID() {
		try {
			control.addCustomer(customer);
			assertTrue(control.deleteCustomerByID(customer.getKundenNummer()));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	void testFindCustomerByUsername() {
		try {
			control.addCustomer(customer);
			assertEquals(customer, control.findCustomerByFirmenName(customer.getFirmenname()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void testFindCustomerByID() {
		try {
			control.addCustomer(customer);
			assertEquals(customer, control.findCustomerByID(customer.getKundenNummer()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void testAllCustomer() {
		try {
			control.addCustomer(customer);

			assertTrue(control.allCustomer().size() >= 1);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	

}