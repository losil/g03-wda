package ch.hslu.swde.wda.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RestWdaData implements Serializable {

	private static final long serialVersionUID = 9096681123493852158L;
    private int id;
    private City city;
    private String data;

    public RestWdaData() {

    }

    public RestWdaData(final int id, final City city, final String data) {
        this.id = id;
        this.city = city;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestWdaData that = (RestWdaData) o;
        return id == that.id &&
                Objects.equals(city, that.city) &&
                Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, city, data);
    }

    @Override
    public String toString() {
        return "RestWdaData{" +
                "id=" + id +
                ", city=" + city +
                ", data='" + data + '\'' +
                '}';
    }

}
