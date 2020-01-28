package ch.hslu.swde.wda.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Operator implements Serializable {

	private static final long serialVersionUID = -6615110684578153596L;

	@Id
	@GeneratedValue
	private int personalnummer;
	private String name;
	private String vorname;
	private String username;
	private String passwort;

	public Operator() {

	}

	public Operator(int personalnummer, String name, String vorname, String username, String passwort) {

		this.personalnummer = personalnummer;
		this.name = name;
		this.vorname = vorname;
		this.username = username;
		this.passwort = passwort;
	}

	public int getPersonalnummer() {
		return personalnummer;
	}

	public void setPersonalnummer(int personalnummer) {
		this.personalnummer = personalnummer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Operator operator = (Operator) o;
		return personalnummer == operator.personalnummer &&
				Objects.equals(name, operator.name) &&
				Objects.equals(vorname, operator.vorname) &&
				Objects.equals(username, operator.username) &&
				Objects.equals(passwort, operator.passwort);
	}

	@Override
	public int hashCode() {
		return Objects.hash(personalnummer, name, vorname, username, passwort);
	}

	@Override
	public String toString() {
		return "Operator{" +
				"personalnummer=" + personalnummer +
				", name='" + name + '\'' +
				", vorname='" + vorname + '\'' +
				", username='" + username + '\'' +
				", passwort='" + passwort + '\'' +
				'}';
	}

}
