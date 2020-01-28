package ch.hslu.swde.wda.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class City implements Serializable {
	private static final long serialVersionUID = 1431511971868142771L;
	@Id
	private int id;
	private String name;

	public City() {

	}

	public City(final int id, final String name) {
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		City city = (City) o;
		return id == city.id && Objects.equals(name, city.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public String toString() {
		return "City{" + "id=" + id + ", name='" + name + '\'' + '}';
	}

}
