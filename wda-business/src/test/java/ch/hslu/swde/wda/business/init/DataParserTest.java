package ch.hslu.swde.wda.business.init;

import ch.hslu.swde.wda.domain.City;
import ch.hslu.swde.wda.domain.RestWdaData;
import ch.hslu.swde.wda.domain.WeatherData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DataParserTest {

    /*
    Testing that parsing is working
    Objects should not be null
     */
    @Test
    void testParseToString() {
        String data = "LAST_UPDATE_TIME:2019-02-11T15:20#COUNTRY:CH#CITY_NAME:Zug#LONGITUDE:8.52#LATIUDE:47.17#" +
                "STATION_ID:2657908#WEATHER_SUMMARY:Rain#WEATHER_DESCRIPTION:shower rain#" +
                "CURRENT_TEMPERATURE_CELSIUS:4.0#PRESSURE:1023#HUMIDITY:90#WIND_SPEED:4.1#WIND_DIRECTION:240";
        DataParser dataParser = new DataParser();
        RestWdaData restWdaData = new RestWdaData(12,new City(1, "Adelboden"), data);
        WeatherData weatherData = dataParser.parseRestWdaData(restWdaData);

        assertNotNull(weatherData);
        assertNotNull(weatherData.getStation());
        assertNotNull(weatherData.getCity());


    }

    @Test
    void testEquals1() {
        DataParser dataParser = new DataParser();
        DataParser dataParser1 = dataParser;
        assertSame(dataParser, dataParser1);
    }

    @Test
    void testHashCode1() {
        DataParser dataParser = new DataParser();
        DataParser dataParser1 = dataParser;
        assertEquals(dataParser.hashCode(), dataParser1.hashCode());
    }

    @Test
    void testToString1() {
        DataParser dataParser = new DataParser();
        assertNotNull(dataParser.toString());
    }

    @Test
    void testGetWeatherDataList() {
        DataParser dataParser = new DataParser();
        assertNull(dataParser.getWeatherDataList());
    }

    @Test
    void testGetRestWdaData() {
        DataParser dataParser = new DataParser();
        assertNull(dataParser.getRestWdaData());
    }
}
