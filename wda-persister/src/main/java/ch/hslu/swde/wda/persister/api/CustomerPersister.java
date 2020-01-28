package ch.hslu.swde.wda.persister.api;

import java.util.List;

import ch.hslu.swde.wda.domain.Customer;

public interface CustomerPersister {
	/**
	 * Speichert den übergebenen Customer.
	 *
	 * @param customer der zu speichernde Customer
	 * @return der gespeicherte Customer
	 * @throws Exception falls das Speichern misslingen sollte
	 */
	Customer add(Customer customer) throws Exception;

	/**
	 * Aktualisiert einen übergebenen Customer.
	 *
	 * @param customer der neue Customer
	 * @return der aktuallisierte Customer
	 * @throws Exception falls die Aktualisierung misslingen sollte
	 */
	Customer update(Customer customer) throws Exception;

	/**
	 * Entfernt den übergebenen Customer aus dem Datenbestand.
	 *
	 * @param customer der zu löschende Costumer
	 * @return <b>true</b> falls erfolgreich sonst <b>false</b>.
	 * @throws Exception falls das Löschen misslingen sollte
	 */
	boolean delete(Customer customer) throws Exception;

	/**
	 * Entfernt den Customer anhander der übergebenen kundennummer aus dem
	 * Datenbestand.
	 *
	 * @param kundenNummer des zu löschenden Customer
	 * @return <b>true</b> falls erfolgreich sonst <b>false</b>.
	 * @throws Exception falls das Löschen misslingen sollte
	 */
	boolean deleteByID(int kundenNummer) throws Exception;

	/**
	 * Liefert den Customer für den übergebenen Firmennamen zurück.
	 *
	 * @param firmenname des Customer
	 * @return gefundener Customer
	 * @throws Exception falls die Suche misslingen sollte
	 */
	Customer findByFirmenName(String firmenName) throws Exception;

	/**
	 * Liefert den Customer für die übergebene Kundennummer zurück.
	 *
	 * @param kundenNummer des Customers
	 * @return der gefundene Customer
	 * @throws Exception falls die Suche misslingen sollte
	 */
	Customer findByID(int kundenNummer) throws Exception;

	/**
	 * Liefert alle Customer zurück.
	 *
	 * @return die Liste mit allen im Datenbestand enthaltenen Customer
	 * @throws Exception falls die Suche misslingen sollte
	 */
	List<Customer> all() throws Exception;
	
	
}
