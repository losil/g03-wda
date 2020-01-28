package ch.hslu.swde.wda.persister.api;

import java.util.List;

import ch.hslu.swde.wda.domain.City;

public interface CityPersister {
	/**
	 * Speichert die übergebene City.
	 *
	 * @param city die zu speichernde City
	 * @return die gespeicherte City
	 * @throws Exception falls das Speichern misslingen sollte
	 */
	City add(City city) throws Exception;

	/**
	 * Aktualisiert die übergebene City.
	 *
	 * @param city die neue City
	 * @return die aktualisierte City
	 * @throws Exception falls die Aktualisierung misslingen sollte
	 */
	City update(City city) throws Exception;

	/**
	 * Entfernt die übergehbene City aus dem Datenbestand.
	 *
	 * @param city die zu löschende City
	 * @return <b>true</b> falls erfolgreich sonst <b>false</b>.
	 * @throws Exception falls das Löschen misslingen sollte
	 */
	boolean delete(City city) throws Exception;

	/**
	 * Entfernt die City für die übergebene City-ID aus dem Datenbestand.
	 *
	 * @param city_ID die City ID der zu löschende City
	 * @return <b>true</b> falls erfolgreich sonst <b>false</b>.
	 * @throws Exception falls das Löschen misslingen sollte
	 */
	boolean deleteByID(int city_ID) throws Exception;

	/**
	 * Liefert die City für den übergebenen City-Namen zurück.
	 *
	 * @param city_name der Name
	 * @return die Liste mit allen gefundenen Citys
	 * @throws Exception falls die Suche misslingen sollte
	 */
	City findByName(String city_name) throws Exception;

	/**
	 * Liefert die City für die übergebene City ID zurück.
	 *
	 * @param city_ID die City ID
	 * @return die gefundene City
	 * @throws Exception falls die Suche misslingen sollte
	 */
	City findByID(int city_ID) throws Exception;

	/**
	 * Liefert alle Citys zurück.
	 *
	 * @return die Liste mit allen im Datenbestand enthaltenen Citys
	 * @throws Exception falls die Suche misslingen sollte
	 */
	List<City> all() throws Exception;

}
