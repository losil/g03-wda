package ch.hslu.swde.wda.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperatorTest {

    private Operator operator = null;

    @BeforeEach
    void setUp() {
        operator = new Operator(100, "Meier", "Patrick", "pmeier", "password");

    }

    @Test
    void testGetPersonalNummer() {
        assertEquals(100, operator.getPersonalnummer());
    }

    @Test
    void testSetPersonalNummer() {
        operator.setPersonalnummer(101);
        assertEquals(101, operator.getPersonalnummer());
    }

    @Test
    void testGetName() {
        assertEquals("Meier", operator.getName());
    }

    @Test
    void testSetName() {
        operator.setName("Müller");
        assertEquals("Müller", operator.getName());
    }

    @Test
    void testGetVorname() {
        assertEquals("Patrick", operator.getVorname());
    }

    @Test
    void testSetVorname() {
        operator.setVorname("Pascal");
        assertEquals("Pascal", operator.getVorname());
    }

    @Test
    void testGetUsername() {
        assertEquals("pmeier", operator.getUsername());
    }

    @Test
    void testSetUsername() {
        operator.setUsername("pm");
        assertEquals("pm", operator.getUsername());
    }

    @Test
    void testGetPasswort() {
        assertEquals("password", operator.getPasswort());
    }

    @Test
    void testSetPasswort() {
        operator.setPasswort("pw");
        assertEquals("pw", operator.getPasswort());
    }

    @Test
    void testEquals1() {
        Operator operator1 = operator;
        assertSame(operator, operator1);
    }

    @Test
    void testHashCode1() {
        Operator operator1 = operator;
        assertEquals(operator1.hashCode(), operator.hashCode());
    }

    @Test
    void testToString1() {
        assertNotNull(operator.toString());
    }
}