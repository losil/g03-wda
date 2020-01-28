package ch.hslu.swde.wda.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StationTest {

	@Test
	void testSetId() {
		Station station = new Station(1, 11.1f, 12.2f);
		station.setId(2);
		assertEquals(station.getId(), 2);
	}

	@Test
	void testGetId() {
		Station station = new Station(1, 11.1f, 12.2f);
		assertEquals(station.getId(), 1);
	}

	@Test
	void testGetLongitude() {
		Station station = new Station(1, 11.1f, 12.2f);
		assertEquals(station.getLongitude(), 11.1f);
	}

	@Test
	void testSetLongitude() {
		Station station = new Station(1, 11.1f, 12.2f);
		station.setLongitude(5);
		assertEquals(station.getLongitude(), 5);

	}

	@Test
	void testGetLatitude() {
		Station station = new Station(1, 11.1f, 12.2f);
		assertEquals(station.getLatitude(), 12.2f);
	}

	@Test
    void testSetLatitude() {
        Station station = new Station(1, 11.1f, 12.2f);
        station.setLatitude(5);
        assertEquals(station.getLatitude(), 5);

    }

    @Test
    void testEquals1() {
        Station station = new Station(1, 11.f, 12.2f);
        Station station1 = station;
        assertSame(station, station1);
    }

    @Test
    void testHashCode1() {
        Station station = new Station(1, 11.f, 12.2f);
        Station station1 = station;
        assertEquals(station.hashCode(), station1.hashCode());
    }

    @Test
    void testToString1() {
        Station station = new Station(1, 11f, 12f);
        assertNotNull(station.toString());
    }
}