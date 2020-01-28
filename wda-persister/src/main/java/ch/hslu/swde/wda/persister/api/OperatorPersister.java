package ch.hslu.swde.wda.persister.api;

import java.util.List;

import ch.hslu.swde.wda.domain.Operator;

public interface OperatorPersister {
	/**
	 * Speichert den übergebenen Operator.
	 *
	 * @param user der zu speichernde Operator
	 * @return der gespeicherte Operator
	 * @throws Exception falls das Speichern misslingen sollte
	 */
	Operator add(Operator operator) throws Exception;

	/**
	 * Aktualisiert einen übergebenen Operator.
	 *
	 * @param user der neue Operator
	 * @return dder aktuallisierte Operator
	 * @throws Exception falls die Aktualisierung misslingen sollte
	 */
	Operator update(Operator operator) throws Exception;

	/**
	 * Entfernt den übergebenen Operator aus dem Datenbestand.
	 *
	 * @param user der zu löschende Operator
	 * @return <b>true</b> falls erfolgreich sonst <b>false</b>.
	 * @throws Exception falls das Löschen misslingen sollte
	 */
	boolean delete(Operator operator) throws Exception;

	/**
	 * Entfernt den Operator anhander der übergebenen personalnummer aus dem Datenbestand.
	 *
	 * @param personalnummer des zu löschenden Users
	 * @return <b>true</b> falls erfolgreich sonst <b>false</b>.
	 * @throws Exception falls das Löschen misslingen sollte
	 */
	boolean deleteByID(int personalNummer) throws Exception;

	/**
	 * Liefert den Operator für den übergebenen Usernamen zurück.
	 *
	 * @param username des Users
	 * @return gefundener Operator
	 * @throws Exception falls die Suche misslingen sollte
	 */
	Operator findByUsername(String username) throws Exception;

	/**
	 * Liefert den Operator für die übergebene personalnummer zurück.
	 *
	 * @param personalNummer des Users
	 * @return der gefundene Operator
	 * @throws Exception falls die Suche misslingen sollte
	 */
	Operator findByID(int personalNummer) throws Exception;

	/**
	 * Liefert alle CUser zurück.
	 *
	 * @return die Liste mit allen im Datenbestand enthaltenen Usern
	 * @throws Exception falls die Suche misslingen sollte
	 */
	List<Operator> all() throws Exception;
}
