package ch.hslu.swde.wda.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestWdaDataTest {
    private RestWdaData restWdaData = null;

    @BeforeEach
    void setup() {
        this.restWdaData = new RestWdaData(100, new City(6000, "Luzern"), "data");
    }

    @Test
    void testGetId() {
        assertEquals(100, restWdaData.getId());
    }

    @Test
    void testSetId() {
        restWdaData.setId(101);
        assertEquals(101, restWdaData.getId());
    }

    @Test
    void testGetCity() {
        assertEquals("Luzern", restWdaData.getCity().getName());
        assertEquals(6000, restWdaData.getCity().getId());
    }

    @Test
    void testSetCity() {
        restWdaData.setCity(new City(8000, "Zürich"));
        assertEquals("Zürich", restWdaData.getCity().getName());
        assertEquals(8000, restWdaData.getCity().getId());
    }

    @Test
    void testGetData() {
        assertEquals("data", restWdaData.getData());
    }

    @Test
    void testSetData() {
        restWdaData.setData("new data");
        assertEquals("new data", restWdaData.getData());
    }

    @Test
    void testEquals1() {
        RestWdaData restWdaData1 = restWdaData;
        assertTrue(restWdaData.equals(restWdaData1));
    }

    @Test
    void testHashCode1() {
        RestWdaData restWdaData1 = restWdaData;
        assertEquals(restWdaData1.hashCode(), restWdaData.hashCode());
    }

    @Test
    void testToString1() {
        assertNotNull(restWdaData.toString());
    }
}