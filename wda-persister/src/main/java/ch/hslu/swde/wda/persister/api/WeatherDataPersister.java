package ch.hslu.swde.wda.persister.api;

import ch.hslu.swde.wda.domain.WeatherData;

import java.sql.Timestamp;
import java.util.List;

/**
 * Diese Schnittstelle gibt die Funktionalitäten vor, welche für die
 * Persistierung von WetterDaten benötigt werden.
 *
 * @author Simon Meier
 * @version 1.0
 */

public interface WeatherDataPersister {
	/**
	 * Speichert die übergebenen DatenWerte.
	 *
	 * @param weatherData die zu speichernden Datenwerte
	 * @return die gespeicherten Datenwerte
	 * @throws Exception falls das Speichern misslingen sollte
	 */
	WeatherData add(WeatherData weatherData) throws Exception;

	/**
	 * Aktualisiert die übergebenen Datenwerte.
	 *
	 * @param weatherData die neuen Datenwerte
	 * @return die aktualisierten Datenwerte
	 * @throws Exception falls die Aktualisierung misslingen sollte
	 */
	WeatherData update(WeatherData weatherData) throws Exception;

	/**
	 * Entfernt die übergehbenen Dtaenwerte aus dem Datenbestand.
	 *
	 * @param weatherData die zu löschenden Datenwerte
	 * @return <b>true</b> falls erfolgreich sonst <b>false</b>.
	 * @throws Exception falls das Löschen misslingen sollte
	 */
	boolean delete(WeatherData weatherData) throws Exception;

	/**
	 * Entfernt die Datenwerte für die übergebene Datensatz ID aus dem Datenbestand.
	 *
	 * @param data_id die Datensatz ID des Datenbestandes welcher gelöscht werden
	 *                soll.
	 * @return <b>true</b> falls erfolgreich sonst <b>false</b>.
	 * @throws Exception falls das Löschen misslingen sollte
	 */
	boolean deleteByID(int data_id) throws Exception;

	/**
	 * Liefert den Datensatz für die übergebene Datensatz ID zurück.
	 *
	 * @param data_id die Datensatz ID
	 * @return der gefundene Datensatz
	 * @throws Exception falls die Suche misslingen sollte
	 */
	WeatherData findByID(int data_id) throws Exception;

	/**
	 * Liefert alle Datenäsatze welche in diesem Zeitraum liegen.
	 *
	 * @return Liste mit allen im Datenbestand enthaltenen Datensätze (Zu der gewählten zeitspanne)
	 * @throws Exception falls die Suche misslingen sollte
	 */
	List<WeatherData> findByDate(Timestamp startDate, Timestamp endDate,  int city_id);
	
	/**
	 * Liefert alle Datenäsatze zurück.
	 *
	 * @return Liste mit allen im Datenbestand enthaltenen Datensätze
	 * @throws Exception falls die Suche misslingen sollte
	 */
	List<WeatherData> all() throws Exception;

	/**
	 * Returns data of the last 24 hours of a city
	 *
	 * @param city_id name of the city
	 * @return List with all matching WeatherData
	 */
	List<WeatherData> getLast24Hours(final int city_id);


}
