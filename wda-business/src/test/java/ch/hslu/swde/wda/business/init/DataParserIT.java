package ch.hslu.swde.wda.business.init;

import ch.hslu.swde.wda.domain.City;
import ch.hslu.swde.wda.domain.RestWdaData;
import ch.hslu.swde.wda.domain.WeatherData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DataParserIT {

    /*
    Testing integration with RestClient
     */
    @Test
    void testParseData() {
        DataParser dataParser = new DataParser();
        dataParser.downloadWheaterData();
        dataParser.parseData();
        dataParser.getWeatherDataList();
        assertFalse(dataParser.getWeatherDataList().isEmpty());

    }

    @Test
    void testParseToString() {
        String data = "LAST_UPDATE_TIME:2019-02-11T15:20#COUNTRY:CH#CITY_NAME:Zug#LONGITUDE:8.52#LATIUDE:47.17#" +
                "STATION_ID:2657908#WEATHER_SUMMARY:Rain#WEATHER_DESCRIPTION:shower rain#" +
                "CURRENT_TEMPERATURE_CELSIUS:4.0#PRESSURE:1023#HUMIDITY:90#WIND_SPEED:4.1#WIND_DIRECTION:240";
        DataParser dataParser = new DataParser();
        RestWdaData restWdaData = new RestWdaData(12, new City(1, "Adelboden"), data);
        WeatherData weatherData = dataParser.parseRestWdaData(restWdaData);
        assertNotNull(weatherData);


    }
}