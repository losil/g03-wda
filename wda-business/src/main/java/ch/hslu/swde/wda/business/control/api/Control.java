package ch.hslu.swde.wda.business.control.api;

import ch.hslu.swde.wda.domain.City;
import ch.hslu.swde.wda.domain.Customer;
import ch.hslu.swde.wda.domain.Operator;
import ch.hslu.swde.wda.domain.WeatherData;

import java.rmi.Remote;
import java.sql.Timestamp;
import java.util.List;

/**
 * This interface provides functions which are needed for control of weather
 * data. Extends Remote because of RMI
 *
 * @author Silvan Loser
 */
public interface Control extends Remote {

	/**
	 * Saves new WeatherData to database
	 *
	 * @param weatherData to save
	 * @return saved weather data
	 * @throws Exception when data could not be saved
	 */
	WeatherData addWeatherData(final WeatherData weatherData) throws Exception;

	/**
	 * Updates existing weather data in database
	 *
	 * @param weatherData to update
	 * @return saved weather data
	 * @throws Exception when data could not be updated
	 */
	WeatherData updateWeatherData(final WeatherData weatherData) throws Exception;

	/**
	 * Deletes existing data from database
	 * 
	 * @param weatherData data to delte
	 * @return true if data is deleted
	 * @throws Exception when error occures
	 */
	boolean deleteWeatherData(final WeatherData weatherData) throws Exception;

	/**
	 * Find a specific Weather Data point, based on id
	 *
	 * @param id to search
	 * @return wheater data object
	 * @throws Exception when error in searching data happens
	 */
	WeatherData findWeatherDataById(final int id) throws Exception;

	/**
	 * Find all Wheater Data which matching a specific city
	 *
	 * @param city of all weather data
	 * @return list with all matching data
	 * @throws Exception when error occurs
	 */
	List<WeatherData> findWeatherDataByCity(final City city) throws Exception;

	/**
	 * Return all WeatherData which is found in database
	 *
	 * @return list with all WeatherData objects
	 * @throws Exception when error in searching data occurs
	 */
	List<WeatherData> findAllWeatherData() throws Exception;

	/**
	 * Add a new City to database
	 *
	 * @param city to save
	 * @return saved city
	 * @throws Exception when city cannot be saved
	 */
	City addCity(final City city) throws Exception;

	/**
	 * Updates a city in database
	 *
	 * @param city to update
	 * @return updated city
	 * @throws Exception when error in updating city occurs
	 */
	City updateCity(final City city) throws Exception;

	/**
	 * Deletes city from database
	 * 
	 * @param city to delte
	 * @return true if city is deleted
	 * @throws Exception when error occurs
	 */
	boolean deleteCity(final City city) throws Exception;

	/**
	 * Finds city by ID
	 *
	 * @param id id as PLZ
	 * @return found city
	 * @throws Exception when city cannot be found
	 */
	City findCityById(final int id) throws Exception;

	/**
	 * Finds city by name
	 *
	 * @param name to search
	 * @return found city object
	 * @throws Exception if error happens
	 */
	City findCityByName(final String name) throws Exception;

	/**
	 * Find all cities in database
	 *
	 * @return list with all found city
	 * @throws Exception when error happens
	 */
	List<City> findAllCities() throws Exception;

	/**
	 * Name of the Remote-Object
	 */
	String RO_NAME = "WDA_Control_RO";

	/**
	 * Returns Data by given date
	 * 
	 * @param startDate Startdatum
	 * @param endDate   Endatum
	 * @param city_id   ID der City
	 * @return list with matching weatherdata
	 * @throws Exception when error occurs
	 * 
	 */
	List<WeatherData> findByDate(Timestamp startDate, Timestamp endDate, int city_id) throws Exception;

	/**
	 * Returns weather data of the last 24 hours by given city.
	 *
	 * @param city_id ID der City
	 * @return list with weatherdata of last 24h per by city
	 * @throws Exception when error occurs
	 */
	List<WeatherData> getLast24HoursData(final int city_id) throws Exception;

	/**
	 * Speichert den übergebenen Operator.
	 *
	 * @param operator der zu speichernde Operator
	 * @return der gespeicherte Operator
	 * @throws Exception falls das Speichern misslingen sollte
	 */
	Operator addOperator(Operator operator) throws Exception;

	/**
	 * Aktualisiert einen übergebenen Operator.
	 *
	 * @param operator der neue Operator
	 * @return dder aktuallisierte Operator
	 * @throws Exception falls die Aktualisierung misslingen sollte
	 */
	Operator updateOperator(Operator operator) throws Exception;

	/**
	 * Entfernt den übergebenen Operator aus dem Datenbestand.
	 *
	 * @param operator der zu löschende Operator
	 * @return <b>true</b> falls erfolgreich sonst <b>false</b>.
	 * @throws Exception falls das Löschen misslingen sollte
	 */
	boolean deleteOperator(Operator operator) throws Exception;

	/**
	 * Entfernt den Operator anhander der übergebenen personalnummer aus dem
	 * Datenbestand.
	 *
	 * @param personalNummer des zu löschenden Users
	 * @return <b>true</b> falls erfolgreich sonst <b>false</b>.
	 * @throws Exception falls das Löschen misslingen sollte
	 */
	boolean deleteOperatorByID(int personalNummer) throws Exception;

	/**
	 * Liefert den Operator für den übergebenen Usernamen zurück.
	 *
	 * @param username des Users
	 * @return gefundener Operator
	 * @throws Exception falls die Suche misslingen sollte
	 */
	Operator findOperatorByUsername(String username) throws Exception;

	/**
	 * Liefert den Operator für die übergebene personalnummer zurück.
	 *
	 * @param personalNummer des Users
	 * @return der gefundene Operator
	 * @throws Exception falls die Suche misslingen sollte
	 */
	Operator findOperatorByID(int personalNummer) throws Exception;

	/**
	 * Liefert alle CUser zurück.
	 *
	 * @return die Liste mit allen im Datenbestand enthaltenen Usern
	 * @throws Exception falls die Suche misslingen sollte
	 */
	List<Operator> allOperator() throws Exception;

	/**
	 * Speichert den übergebenen Customer.
	 *
	 * @param customer der zu speichernde Customer
	 * @return der gespeicherte Customer
	 * @throws Exception falls das Speichern misslingen sollte
	 */
	Customer addCustomer(Customer customer) throws Exception;

	/**
	 * Aktualisiert einen übergebenen Customer.
	 *
	 * @param customer der neue Customer
	 * @return der aktuallisierte Customer
	 * @throws Exception falls die Aktualisierung misslingen sollte
	 */
	Customer updateCustomer(Customer customer) throws Exception;

	/**
	 * Entfernt den übergebenen Customer aus dem Datenbestand.
	 *
	 * @param customer der zu löschende Costumer
	 * @return <b>true</b> falls erfolgreich sonst <b>false</b>.
	 * @throws Exception falls das Löschen misslingen sollte
	 */
	boolean deleteCustomer(Customer customer) throws Exception;

	/**
	 * Entfernt den Customer anhander der übergebenen kundennummer aus dem
	 * Datenbestand.
	 *
	 * @param kundenNummer des zu löschenden Customer
	 * @return <b>true</b> falls erfolgreich sonst <b>false</b>.
	 * @throws Exception falls das Löschen misslingen sollte
	 */
	boolean deleteCustomerByID(int kundenNummer) throws Exception;

	/**
	 * Liefert den Customer für den übergebenen Firmennamen zurück.
	 *
	 * @param firmenName des Customer
	 * @return gefundener Customer
	 * @throws Exception falls die Suche misslingen sollte
	 */
	Customer findCustomerByFirmenName(String firmenName) throws Exception;

	/**
	 * Liefert den Customer für die übergebene Kundennummer zurück.
	 *
	 * @param kundenNummer des Customers
	 * @return der gefundene Customer
	 * @throws Exception falls die Suche misslingen sollte
	 */
	Customer findCustomerByID(int kundenNummer) throws Exception;

	/**
	 * Liefert alle Customer zurück.
	 *
	 * @return die Liste mit allen im Datenbestand enthaltenen Customer
	 * @throws Exception falls die Suche misslingen sollte
	 */
	List<Customer> allCustomer() throws Exception;

}
