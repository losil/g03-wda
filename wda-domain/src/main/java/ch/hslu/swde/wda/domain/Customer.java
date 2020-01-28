package ch.hslu.swde.wda.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Customer implements Serializable {

	@Id
	@GeneratedValue
	private int kundenNummer;
	/** Firmenname. */
	private String firmenname;
	/** Firmenadresse. */
	private String strasse;
	private String strassenBezeichnung;
	private int plz;
	private String ort;
	/** Ansprechspartner. */
	private String ansprechspartner;
	private String telefon;
	/** ALizenz/Service */
	private String lizenzart;

	public Customer(int kundennummer ,String firmenname, String strasse, String strassenBezeichnung, int plz, String ort,
			String ansprechspartner, String telefon, String lizenzart) {
		this.kundenNummer= kundennummer;
		this.firmenname = firmenname;
		this.strasse = strasse;
		this.strassenBezeichnung = strassenBezeichnung;
		this.plz = plz;
		this.ort = ort;
		this.ansprechspartner = ansprechspartner;
		this.telefon = telefon;
		this.lizenzart = lizenzart;

	}

	public Customer() {

	}

	public int getKundenNummer() {
		return kundenNummer;
	}

	public void setKundenNummer(int kundenNummer) {
		this.kundenNummer = kundenNummer;
	}

	public String getFirmenname() {
		return firmenname;
	}

	public void setFirmenname(String firmenname) {
		this.firmenname = firmenname;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public String getStrassenBezeichnung() {
		return strassenBezeichnung;
	}

	public void setStrassenBezeichnung(String strassenBezeichnung) {
		this.strassenBezeichnung = strassenBezeichnung;
	}

	public int getPlz() {
		return plz;
	}

	public void setPlz(int plz) {
		this.plz = plz;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public String getAnsprechspartner() {
		return ansprechspartner;
	}

	public void setAnsprechspartner(String ansprechspartner) {
		this.ansprechspartner = ansprechspartner;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getLizenzart() {
		return lizenzart;
	}

	public void setLizenzart(String lizenzart) {
		this.lizenzart = lizenzart;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Customer customer = (Customer) o;
		return kundenNummer == customer.kundenNummer &&
				plz == customer.plz &&
				Objects.equals(firmenname, customer.firmenname) &&
				Objects.equals(strasse, customer.strasse) &&
				Objects.equals(strassenBezeichnung, customer.strassenBezeichnung) &&
				Objects.equals(ort, customer.ort) &&
				Objects.equals(ansprechspartner, customer.ansprechspartner) &&
				Objects.equals(telefon, customer.telefon) &&
				Objects.equals(lizenzart, customer.lizenzart);
	}

	@Override
	public int hashCode() {
		return Objects.hash(kundenNummer, firmenname, strasse, strassenBezeichnung, plz, ort, ansprechspartner, telefon, lizenzart);
	}

	@Override
	public String toString() {
		return "Customer{" +
				"kundenNummer=" + kundenNummer +
				", firmenname='" + firmenname + '\'' +
				", strasse='" + strasse + '\'' +
				", strassenBezeichnung='" + strassenBezeichnung + '\'' +
				", plz=" + plz +
				", ort='" + ort + '\'' +
				", ansprechspartner='" + ansprechspartner + '\'' +
				", telefon='" + telefon + '\'' +
				", lizenzart='" + lizenzart + '\'' +
				'}';
	}

}
