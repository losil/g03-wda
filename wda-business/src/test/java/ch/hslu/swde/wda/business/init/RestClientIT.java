package ch.hslu.swde.wda.business.init;

import ch.hslu.swde.wda.domain.City;
import ch.hslu.swde.wda.domain.RestWdaData;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

class RestClientIT {

    /*
    Test that returned list is not empty
     */
    @Test
    void testGetCities() {
        RestClient restClient = new RestClient("http://swde.el.eee.intern:8080/weatherdata-rws-provider/rest/weatherdata");
        List<City> cities = restClient.getCities();
        assertFalse(cities.isEmpty());
    }

    /*
    Test that returned list is not empty
     */
    @Test
    void testGetWeatherData() {
        RestClient restClient = new RestClient("http://swde.el.eee.intern:8080/weatherdata-rws-provider/rest/weatherdata");
        List<RestWdaData> wdaData = restClient.getAllWdaData();
        assertFalse(wdaData.isEmpty());
    }

    /*
    Test
     */
    @Test
    void testGetDayWeatherData() {
        RestClient restClient = new RestClient("http://swde.el.eee.intern:8080/weatherdata-rws-provider/rest/weatherdata");
        assertFalse(restClient.getDayWeatherData().isEmpty());
    }
}