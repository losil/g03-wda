package ch.hslu.swde.wda.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class WeatherData implements Serializable {

	private static final long serialVersionUID = -1300403172534310699L;

	@Id
	private int id;
	@ManyToOne
	private City city;
	@ManyToOne
	private Station station;
	private Timestamp timestamp;
	private float temperatureInCelsius;
	private float windSpeed;
	private String windDirection;
	private int pressure;
	private int humidity;
	private String weatherDescription;
	private String weatherSummary;

	// to do..
	public WeatherData() {

	}

	public WeatherData(final int id, final City city, final Station station, Timestamp timestamp,
	                   final float temperatureInCelsius, final float windSpeed, final String windDirection, final int pressure,
	                   final int humidity, final String weatherDescription, final String weatherSummary) {
		this.id = id;
		this.city = city;
		this.station = station;
		this.timestamp = timestamp;
		this.temperatureInCelsius = temperatureInCelsius;
		this.windSpeed = windSpeed;
		this.windDirection = windDirection;
		this.pressure = pressure;
		this.humidity = humidity;
		this.weatherDescription = weatherDescription;
		this.weatherSummary = weatherSummary;
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

	public void setStation(Station station) {
		this.station = station;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public float getWindSpeed() {
		return this.windSpeed;
	}

	public void setWindSpeed(final float windSpeed) {
		this.windSpeed = windSpeed;
	}

	public String getWindDirection() {
		return windDirection;
	}

	public void setWeatherDescription(String weatherDescription) {
		this.weatherDescription = weatherDescription;
	}

	public void setWeatherSummary(String weatherSummary) {
		this.weatherSummary = weatherSummary;
	}

	public Station getStation() {
		return station;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}

	public float getTemperatureInCelsius() {
		return temperatureInCelsius;
	}

	public void setTemperatureInCelsius(float temperatureInCelsius) {
		this.temperatureInCelsius = temperatureInCelsius;
	}

	public int getPressure() {
		return pressure;
	}

	public void setPressure(int pressure) {
		this.pressure = pressure;
	}

	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public String getWeatherDescription() {
		return weatherDescription;
	}

	public String getWeatherSummary() {
		return weatherSummary;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		WeatherData that = (WeatherData) o;
		return id == that.id &&
				Float.compare(that.temperatureInCelsius, temperatureInCelsius) == 0 &&
				Float.compare(that.windSpeed, windSpeed) == 0 &&
				pressure == that.pressure &&
				humidity == that.humidity &&
				Objects.equals(city, that.city) &&
				Objects.equals(station, that.station) &&
				Objects.equals(timestamp, that.timestamp) &&
				Objects.equals(windDirection, that.windDirection) &&
				Objects.equals(weatherDescription, that.weatherDescription) &&
				Objects.equals(weatherSummary, that.weatherSummary);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, city, station, timestamp, temperatureInCelsius, windSpeed, windDirection, pressure, humidity, weatherDescription, weatherSummary);
	}

	@Override
	public String toString() {
		return "WeatherData{" +
				"id=" + id +
				", city=" + city +
				", station=" + station +
				", timestamp=" + timestamp +
				", temperatureInCelsius=" + temperatureInCelsius +
				", windSpeed=" + windSpeed +
				", windDirection='" + windDirection + '\'' +
				", pressure=" + pressure +
				", humidity=" + humidity +
				", weatherDescription='" + weatherDescription + '\'' +
				", weatherSummary='" + weatherSummary + '\'' +
				'}';
	}

}



