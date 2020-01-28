package ch.hslu.swde.wda.business.init;

import ch.hslu.swde.wda.domain.City;
import ch.hslu.swde.wda.domain.RestWdaData;
import ch.hslu.swde.wda.domain.Station;
import ch.hslu.swde.wda.domain.WeatherData;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * This Class is providing methods to fetch data from a remote server and parsing it to WheaterData format.
 */
public class DataParser {

    private List<WeatherData> weatherDataList;
    private List<RestWdaData> restWdaData;

    public DataParser() {

    }

    public List<WeatherData> getWeatherDataList() {
        return weatherDataList;
    }

    public List<RestWdaData> getRestWdaData() {
        return restWdaData;
    }

    /**
     * This method is downloading all WheaterData from source with the RestClient class.
     */
    public void downloadWheaterData() {
        this.restWdaData = new RestClient("http://swde.el.eee.intern:8080/weatherdata-rws-provider/rest/weatherdata").getAllWdaData();
    }

    /**
     * This Method is parsing the output from the WheaterData source.
     * WheaterData Objects are beeing created.
     * @return list with parsed data
     */
    public List<WeatherData> parseData() {
        this.weatherDataList = new ArrayList<>();
        WeatherData weatherData = null;

        /*
        Iterating over all assets fetched by the rest client
         */
        for (RestWdaData r: restWdaData
        ) {
            int id = r.getId();
            String data = r.getData();
            City city = r.getCity();

            /*
            Spliting up the data output from source, List with strings is returned
             */
            List<String> splittedData = new ArrayList<String>(Arrays.asList(data.split("#")));

            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss", Locale.GERMAN);
            LocalDateTime localDateTime;
            /*
            Fix for datasets which don't have seconds in their timestamp
             */
            if (splittedData.get(0).split(":",2)[1].length() == 16) {
                localDateTime = LocalDateTime.parse(splittedData.get(0).split(":", 2)[1] + ":00", inputFormatter);
            } else {
                localDateTime = LocalDateTime.parse(splittedData.get(0).split(":", 2)[1], inputFormatter);
            }

            /*
            Converting LocalDateTime to java.sql.Timestamp for correct saving in database
             */

            Timestamp timestamp = Timestamp.valueOf(localDateTime);

            /*
            Filling up all variables
             */
            float longitude = Float.valueOf(splittedData.get(3).split(":")[1]);
            float latitude = Float.valueOf(splittedData.get(4).split(":")[1]);
            int stationId = Integer.valueOf(splittedData.get(5).split(":")[1]);
            String weatherSummary = splittedData.get(6).split(":")[1];
            String weatherDescription = splittedData.get(7).split(":")[1];
            float temperature = Float.valueOf(splittedData.get(8).split(":")[1]);
            int pressure = Integer.valueOf(splittedData.get(9).split(":")[1]);
            int humidity = Integer.valueOf(splittedData.get(10).split(":")[1]);
            float windSpeed = Float.valueOf(splittedData.get(11).split(":")[1]);
            String windDirection = splittedData.get(12);

            /*
            Creating objects needed for creating a WheaterData Object
             */
            Station station = new Station(stationId, longitude, latitude);

            weatherData = new WeatherData(id, city, station, timestamp, temperature, windSpeed, windDirection, pressure, humidity, weatherDescription, weatherSummary);

            weatherDataList.add(weatherData);

        }

        return weatherDataList;

    }

    /*
    Duplicate code for testing
     */

    public WeatherData parseRestWdaData(final RestWdaData restWdaData) {
        int id = restWdaData.getId();
        String data = restWdaData.getData();
        City city = restWdaData.getCity();

            /*
            Spliting up the data output from source, List with strings is returned
             */
        List<String> splittedData = new ArrayList<String>(Arrays.asList(data.split("#")));

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss", Locale.GERMAN);
        LocalDateTime localDateTime;
            /*
            Fix for datasets which don't have seconds in their timestamp
             */
        if (splittedData.get(0).split(":", 2)[1].length() == 16) {
            localDateTime = LocalDateTime.parse(splittedData.get(0).split(":", 2)[1] + ":00", inputFormatter);
        } else {
            localDateTime = LocalDateTime.parse(splittedData.get(0).split(":", 2)[1], inputFormatter);
        }

            /*
            Converting LocalDateTime to java.sql.Timestamp for correct saving in database
             */

        Timestamp timestamp = Timestamp.valueOf(localDateTime);

            /*
            Filling up all variables
             */
        float longitude = Float.valueOf(splittedData.get(3).split(":")[1]);
        float latitude = Float.valueOf(splittedData.get(4).split(":")[1]);
        int stationId = Integer.valueOf(splittedData.get(5).split(":")[1]);
        String weatherSummary = splittedData.get(6).split(":")[1];
        String weatherDescription = splittedData.get(7).split(":")[1];
        float temperature = Float.valueOf(splittedData.get(8).split(":")[1]);
        int pressure = Integer.valueOf(splittedData.get(9).split(":")[1]);
        int humidity = Integer.valueOf(splittedData.get(10).split(":")[1]);
        float windSpeed = Float.valueOf(splittedData.get(11).split(":")[1]);
        String windDirection = splittedData.get(12).split(":")[1];

            /*
            Creating objects needed for creating a WheaterData Object
             */
        Station station = new Station(stationId, longitude, latitude);

        return new WeatherData(id, city, station, timestamp, temperature, windSpeed, windDirection, pressure, humidity, weatherDescription, weatherSummary);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataParser that = (DataParser) o;
        return Objects.equals(weatherDataList, that.weatherDataList) &&
                Objects.equals(restWdaData, that.restWdaData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weatherDataList, restWdaData);
    }

    @Override
    public String toString() {
        return "DataParser{" +
                "weatherDataList=" + weatherDataList +
                ", restWdaData=" + restWdaData +
                '}';
    }
}
