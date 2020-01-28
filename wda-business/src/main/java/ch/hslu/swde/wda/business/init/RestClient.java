package ch.hslu.swde.wda.business.init;

import ch.hslu.swde.wda.domain.City;
import ch.hslu.swde.wda.domain.RestWdaData;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * This Class provides a REST Cleint which fetches data from a REST Server,
 */
public class RestClient {

    private Client client;
    private String baseUrl;

    public RestClient(final String baseUrl) {
        this.client = ClientBuilder.newClient( );
        this.baseUrl = baseUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * Fetching cities from the datasource
     * @return List with City objects
     */
    public List<City> getCities() {
        WebTarget webTarget = client.target(baseUrl).path("cities");
        Response response = webTarget.request(MediaType.APPLICATION_JSON).get();
        List<City> cities = response.readEntity(new GenericType<List<City>>() {});
        return cities;
    }

    /**
     * Fetching WheaterData for all Cities
     * @return Returns a List with every WheaterData asset found on the REST Server
     */
    public List<RestWdaData> getAllWdaData() {
        List<RestWdaData> returnList = new ArrayList<>();
        List<RestWdaData> weatherdata;
        for (City c: getCities()
        ) {
            WebTarget webTarget = client.target(baseUrl).path(c.getName());
            Response response = webTarget.request(MediaType.APPLICATION_JSON).get();
            weatherdata = response.readEntity(new GenericType<List<RestWdaData>>(){});
            returnList.addAll(weatherdata);
        }
        return returnList;
    }

    /**
     * Fechting WeatherData for all Cities with data of the day.
     *
     * @return List all RestWdaData objects.
     */
    public List<RestWdaData> getDayWeatherData() {
        LocalDate now = LocalDate.now();
        List<RestWdaData> returnList = new ArrayList<>();
        List<RestWdaData> weatherData;
        String year = String.valueOf(now.getYear());
        String month = String.valueOf(now.getMonthValue());
        String day = String.valueOf(now.getDayOfMonth());
        for (City c : getCities()
        ) {
            /*
            Fetching the weatherdata from the specified day until now
             */
            WebTarget webTarget = client.target(baseUrl + "/" + c.getName() +
                    "/since?year=" + year + "&month=" + month + "&day=" + day);
            Response response = webTarget.request(MediaType.APPLICATION_JSON).get();
            weatherData = response.readEntity(new GenericType<List<RestWdaData>>() {
            });
            returnList.addAll(weatherData);

        }
        return returnList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestClient that = (RestClient) o;
        return Objects.equals(client, that.client) &&
                Objects.equals(baseUrl, that.baseUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(client, baseUrl);
    }

    @Override
    public String toString() {
        return "RestClient{" +
                "client=" + client +
                ", baseUrl='" + baseUrl + '\'' +
                '}';
    }
}
