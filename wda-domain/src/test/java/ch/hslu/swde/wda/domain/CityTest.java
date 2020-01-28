package ch.hslu.swde.wda.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CityTest {

    @Test
    void testGetName() {
        City city = new City(1, "Luzern");
        assertEquals("Luzern", city.getName());
    }

    @Test
    void testSetName() {
        City city = new City(1, "Luzern");
        city.setName("Zug");
        assertEquals("Zug", city.getName());
    }

    @Test
    void testEquals1() {
        City city = new City(1, "Luzern");
        City city1 = city;
        assertEquals(city, city1);
    }

    @Test
    void testHashCode1() {
        City city = new City(1, "Luzern");
        City city1 = city;
        assertEquals(city.hashCode(), city1.hashCode());
    }

    @Test
    void testGetId() {
        City city = new City(1, "Luzern");
        assertEquals(1, city.getId());
    }

    @Test
    void testSetId() {
        City city = new City();
        city.setId(10);
        assertEquals(10, city.getId());
    }

}