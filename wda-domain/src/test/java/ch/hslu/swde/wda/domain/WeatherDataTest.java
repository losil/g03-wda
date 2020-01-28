package ch.hslu.swde.wda.domain;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class WeatherDataTest {

    @Test
    void testgetId() {

        WeatherData weatherData = new WeatherData(1,new City(1,"Luzern"),new Station(1,0,0), new Timestamp(System.currentTimeMillis()),20f,20f,"unknown",1000,100,"sehr schön","schön");
        assertEquals(weatherData.getId(),1);

    }

    @Test
    void testsetId() {
        WeatherData weatherData = new WeatherData(1,new City(1,"Luzern"),new Station(1,0,0), new Timestamp(System.currentTimeMillis()),20f,20f,"unknown",1000,100,"sehr schön","schön");
        weatherData.setId(2);
        assertEquals(weatherData.getId(),2);
    }

    @Test
    void testgetCity() {
        WeatherData weatherData = new WeatherData(1,new City(1,"Luzern"),new Station(1,0,0), new Timestamp(System.currentTimeMillis()),20f,20f,"unknown",1000,100,"sehr schön","schön");
        assertEquals(weatherData.getCity(),new City(1,"Luzern"));
    }

    @Test
    void testsetCity() {
        WeatherData weatherData = new WeatherData(1,new City(1,"Luzern"),new Station(1,0,0), new Timestamp(System.currentTimeMillis()),20f,20f,"unknown",1000,100,"sehr schön","schön");
        weatherData.setCity(new City(2,"Bern"));
        assertEquals(weatherData.getCity(),new City(2,"Bern"));

    }

    @Test
    void testsetStation() {
        WeatherData weatherData = new WeatherData(1,new City(1,"Luzern"),new Station(1,0,0), new Timestamp(System.currentTimeMillis()),20f,20f,"unknown",1000,100,"sehr schön","schön");
        weatherData.setStation(new Station(2,1,1));
        assertEquals(weatherData.getStation(),new Station(2,1,1));
    }

    @Test
    void testgetTimestamp() {
        Timestamp timestamp =  new Timestamp(System.currentTimeMillis());
        WeatherData weatherData = new WeatherData(1,new City(1,"Luzern"),new Station(1,0,0), timestamp,20f,20f,"unknown",1000,100,"sehr schön","schön");
        assertEquals(weatherData.getTimestamp(),timestamp);
    }

    @Test
    void testgetWindSpeed() {
        WeatherData weatherData = new WeatherData(1,new City(1,"Luzern"),new Station(1,0,0), new Timestamp(System.currentTimeMillis()),20f,20f,"unknown",1000,100,"sehr schön","schön");
        assertEquals(weatherData.getWindSpeed(),20f);
    }

    @Test
    void testsetWindSpeed() {
        WeatherData weatherData = new WeatherData(1,new City(1,"Luzern"),new Station(1,0,0), new Timestamp(System.currentTimeMillis()),20f,20f,"unknown",1000,100,"sehr schön","schön");
        weatherData.setWindSpeed(0f);
        assertEquals(weatherData.getWindSpeed(),0f);
    }

    @Test
    void testgetWindDirection() {
        WeatherData weatherData = new WeatherData(1,new City(1,"Luzern"),new Station(1,0,0), new Timestamp(System.currentTimeMillis()),20f,20f,"unknown",1000,100,"sehr schön","schön");
        assertEquals(weatherData.getWindDirection(),"unknown");
    }

    @Test
    void testsetWeatherDescription() {
        WeatherData weatherData = new WeatherData(1,new City(1,"Luzern"),new Station(1,0,0), new Timestamp(System.currentTimeMillis()),20f,20f,"unknown",1000,100,"sehr schön","schön");
        weatherData.setWeatherDescription("nicht schön");
        assertEquals(weatherData.getWeatherDescription(),"nicht schön");
    }

    @Test
    void testsetWeatherSummary() {
        WeatherData weatherData = new WeatherData(1,new City(1,"Luzern"),new Station(1,0,0), new Timestamp(System.currentTimeMillis()),20f,20f,"unknown",1000,100,"sehr schön","schön");
        weatherData.setWeatherSummary("gut");
        assertEquals(weatherData.getWeatherSummary(),"gut");
    }

    @Test
    void testgetStation() {
        WeatherData weatherData = new WeatherData(1,new City(1,"Luzern"),new Station(1,0,0), new Timestamp(System.currentTimeMillis()),20f,20f,"unknown",1000,100,"sehr schön","schön");
        assertEquals(weatherData.getStation(),new Station(1,0,0));
    }

    @Test
    void testsetTimestamp() {
        Timestamp timestamp =  new Timestamp(System.currentTimeMillis());
        WeatherData weatherData = new WeatherData(1,new City(1,"Luzern"),new Station(1,0,0), timestamp,20f,20f,"unknown",1000,100,"sehr schön","schön");
        timestamp.setTime(1);
        weatherData.setTimestamp(timestamp);
        assertEquals(weatherData.getTimestamp(),timestamp);
    }

    @Test
    void testsetWindDirection() {
        WeatherData weatherData = new WeatherData(1,new City(1,"Luzern"),new Station(1,0,0), new Timestamp(System.currentTimeMillis()),20f,20f,"unknown",1000,100,"sehr schön","schön");
        weatherData.setWindDirection("nord");
        assertEquals(weatherData.getWindDirection(),"nord");
    }

    @Test
    void testgetTemperatureInCelsius() {
        WeatherData weatherData = new WeatherData(1,new City(1,"Luzern"),new Station(1,0,0), new Timestamp(System.currentTimeMillis()),20f,20f,"unknown",1000,100,"sehr schön","schön");
        assertEquals(weatherData.getTemperatureInCelsius(),20f);
    }

    @Test
    void testsetTemperatureInCelsius() {
        WeatherData weatherData = new WeatherData(1,new City(1,"Luzern"),new Station(1,0,0), new Timestamp(System.currentTimeMillis()),20f,20f,"unknown",1000,100,"sehr schön","schön");
        weatherData.setTemperatureInCelsius(0f);
        assertEquals(weatherData.getTemperatureInCelsius(),0f);
    }

    @Test
    void testgetPressure() {
        WeatherData weatherData = new WeatherData(1,new City(1,"Luzern"),new Station(1,0,0), new Timestamp(System.currentTimeMillis()),20f,20f,"unknown",1000,100,"sehr schön","schön");
        assertEquals(weatherData.getPressure(),1000);
    }

    @Test
    void testsetPressure() {
        WeatherData weatherData = new WeatherData(1,new City(1,"Luzern"),new Station(1,0,0), new Timestamp(System.currentTimeMillis()),20f,20f,"unknown",1000,100,"sehr schön","schön");
        weatherData.setPressure(33);
        assertEquals(weatherData.getPressure(),33);
    }

    @Test
    void testgetHumidity() {
        WeatherData weatherData = new WeatherData(1,new City(1,"Luzern"),new Station(1,0,0), new Timestamp(System.currentTimeMillis()),20f,20f,"unknown",1000,100,"sehr schön","schön");
        assertEquals(weatherData.getHumidity(),100);
    }

    @Test
    void testsetHumidity() {
        WeatherData weatherData = new WeatherData(1,new City(1,"Luzern"),new Station(1,0,0), new Timestamp(System.currentTimeMillis()),20f,20f,"unknown",1000,100,"sehr schön","schön");
        weatherData.setHumidity(33);
        assertEquals(weatherData.getHumidity(),33);
    }

    @Test
    void testgetWeatherDescription() {
        WeatherData weatherData = new WeatherData(1,new City(1,"Luzern"),new Station(1,0,0), new Timestamp(System.currentTimeMillis()),20f,20f,"unknown",1000,100,"sehr schön","schön");
        assertEquals(weatherData.getWeatherDescription(),"sehr schön");
    }

    @Test
    void testgetWeatherSummary() {
        WeatherData weatherData = new WeatherData(1, new City(1, "Luzern"),
                new Station(1, 0, 0), new Timestamp(System.currentTimeMillis()),
                20f, 20f, "unknown", 1000, 100,
                "sehr schön", "schön");
            assertEquals(weatherData.getWeatherSummary(),"schön");
    }


    @Test
    void testEquals1() {
        WeatherData weatherData = new WeatherData(1, new City(1, "Luzern"),
                new Station(1, 0, 0), new Timestamp(System.currentTimeMillis()),
                20f, 20f, "unknown", 1000, 100,
                "sehr schön", "schön");
        WeatherData weatherData1 = weatherData;
        assertSame(weatherData, weatherData1);

    }

    @Test
    void testHashCode1() {
        WeatherData weatherData = new WeatherData(1, new City(1, "Luzern"),
                new Station(1, 0, 0), new Timestamp(System.currentTimeMillis()),
                20f, 20f, "unknown", 1000, 100,
                "sehr schön", "schön");
        WeatherData weatherData1 = weatherData;
        assertEquals(weatherData.hashCode(), weatherData1.hashCode());
    }

    @Test
    void testToString1() {
        WeatherData weatherData = new WeatherData(1, new City(1, "Luzern"),
                new Station(1, 0, 0), new Timestamp(System.currentTimeMillis()),
                20f, 20f, "unknown", 1000, 100,
                "sehr schön", "schön");
        assertNotNull(weatherData.toString());
    }
}