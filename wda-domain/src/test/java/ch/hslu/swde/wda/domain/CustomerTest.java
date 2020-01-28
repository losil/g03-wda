package ch.hslu.swde.wda.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    private Customer customer = null;

    @BeforeEach
    void setup() {
        this.customer = new Customer(99999999, "Testfirma", "Teststrasse",
                "33", 9999, "TestOrt", "Test Ansprechspartner",
                "329093404", "Test-Lizenz");
    }

    @Test
    void testGetKundenNummer() {
        assertEquals(99999999, customer.getKundenNummer());
    }

    @Test
    void testSetKundenNummer() {
        customer.setKundenNummer(11111);
        assertEquals(11111, customer.getKundenNummer());
    }

    @Test
    void testGetFirmenname() {
        assertEquals("Testfirma", customer.getFirmenname());
    }

    @Test
    void testSetFirmenname() {
        customer.setFirmenname("Neue Firma");
        assertEquals("Neue Firma", customer.getFirmenname());
    }

    @Test
    void testGetStrasse() {
        assertEquals("Teststrasse", customer.getStrasse());
    }

    @Test
    void testSetStrasse() {
        customer.setStrasse("Str");
        assertEquals("Str", customer.getStrasse());
    }

    @Test
    void testGetStrassenBezeichnung() {
        assertEquals("33", customer.getStrassenBezeichnung());
    }

    @Test
    void testSetStrassenBezeichnung() {
        customer.setStrassenBezeichnung("11");
        assertEquals("11", customer.getStrassenBezeichnung());
    }

    @Test
    void testGetPlz() {
        assertEquals(9999, customer.getPlz());
    }

    @Test
    void testSetPlz() {
        customer.setPlz(9900);
        assertEquals(9900, customer.getPlz());
    }

    @Test
    void testGetOrt() {
        assertEquals("TestOrt", customer.getOrt());
    }

    @Test
    void testSetOrt() {
        customer.setOrt("NeuerOrt");
        assertEquals("NeuerOrt", customer.getOrt());
    }

    @Test
    void testGetAnsprechspartner() {
        assertEquals("Test Ansprechspartner", customer.getAnsprechspartner());
    }

    @Test
    void testSetAnsprechspartner() {
        customer.setAnsprechspartner("NeuAnspr");
        assertEquals("NeuAnspr", customer.getAnsprechspartner());
    }

    @Test
    void testGetTelefon() {
        assertEquals("329093404", customer.getTelefon());
    }

    @Test
    void testSetTelefon() {
        customer.setTelefon("093450334");
        assertEquals("093450334", customer.getTelefon());
    }

    @Test
    void testGetLizenzart() {
        assertEquals("Test-Lizenz", customer.getLizenzart());
    }

    @Test
    void testSetLizenzart() {
        customer.setLizenzart("New-Lizenz");
        assertEquals("New-Lizenz", customer.getLizenzart());
    }

    @Test
    void testEquals1() {
        Customer customer1 = customer;
        assertTrue(customer1.equals(customer));
    }

    @Test
    void testHashCode1() {
        Customer customer1 = customer;
        assertEquals(customer1, customer);
    }

    @Test
    void testToString1() {
        assertNotNull(customer.toString());
    }
}