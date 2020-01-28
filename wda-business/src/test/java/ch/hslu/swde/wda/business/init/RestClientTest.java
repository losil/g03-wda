package ch.hslu.swde.wda.business.init;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestClientTest {

    private RestClient restClient = null;

    @BeforeEach
    void setUp() {
        this.restClient = new RestClient("http://url.ch");
    }

    @Test
    void testSetBaseUrl() {
        this.restClient.setBaseUrl("http://newurl.ch/");
        assertEquals("http://newurl.ch/", this.restClient.getBaseUrl());
    }

    @Test
    void testGetBaseUrl() {
        assertEquals("http://url.ch", this.restClient.getBaseUrl());
    }

    @Test
    void testEquals1() {
        RestClient restClient1 = restClient;
        assertSame(this.restClient, restClient1);

    }

    @Test
    void testHashCode1() {
        RestClient restClient1 = this.restClient;
        assertEquals(this.restClient.hashCode(), restClient1.hashCode());
    }

    @Test
    void testToString1() {
        assertNotNull(restClient.toString());
    }


}