package ch.hslu.swde.wda.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Station implements Serializable {

	private static final long serialVersionUID = 7980209932037859633L;
	@Id
	@GeneratedValue
	private int id;
	private float longitude;
	private float latitude;
	
	public Station() {
		
	}

	public Station(final int id, final float longitude, final float latitude) {
		this.id = id;
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getLongitude() {
		return longitude;
	}

	void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Station station = (Station) o;
		return id == station.id && Double.compare(station.longitude, longitude) == 0
				&& Double.compare(station.latitude, latitude) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, longitude, latitude);
	}

	@Override
	public String toString() {
		return "Station{" + "id=" + id + ", longitude=" + longitude + ", latitude=" + latitude + '}';
	}

}
