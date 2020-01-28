package ch.hslu.swde.wda.business.control.impl;

import ch.hslu.swde.wda.business.control.api.Control;
import ch.hslu.swde.wda.domain.City;
import ch.hslu.swde.wda.domain.Customer;
import ch.hslu.swde.wda.domain.Operator;
import ch.hslu.swde.wda.domain.WeatherData;
import ch.hslu.swde.wda.persister.api.CustomerPersister;
import ch.hslu.swde.wda.persister.dao.CityJpaPersister;
import ch.hslu.swde.wda.persister.dao.CustomerJpaPersister;
import ch.hslu.swde.wda.persister.dao.OperatorJpaPersister;
import ch.hslu.swde.wda.persister.dao.WeatherDataJpaPersister;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ControlImpl extends UnicastRemoteObject implements Control {

	private final CityJpaPersister cityPersister;
	private final WeatherDataJpaPersister weatherDataPersister;
	private final OperatorJpaPersister operatorPersister;
	private final CustomerPersister customerPersister;

	public ControlImpl() throws RemoteException {
		this.cityPersister = new CityJpaPersister();
		this.weatherDataPersister = new WeatherDataJpaPersister();
		this.operatorPersister = new OperatorJpaPersister();
		this.customerPersister = new CustomerJpaPersister();
	}

	@Override
	public WeatherData addWeatherData(WeatherData weatherData) throws Exception {
		return weatherDataPersister.add(weatherData);
	}

	@Override
	public WeatherData updateWeatherData(WeatherData weatherData) throws Exception {
		return weatherDataPersister.update(weatherData);
	}

	@Override
	public boolean deleteWeatherData(WeatherData weatherData) throws Exception {
		return weatherDataPersister.delete(weatherData);

	}

	@Override
	public WeatherData findWeatherDataById(int id) throws Exception {
		return weatherDataPersister.findByID(id);
	}

	@Override
	public List<WeatherData> findWeatherDataByCity(City city) throws Exception {
		List<WeatherData> weatherDataList = new ArrayList<>();
		for (WeatherData w : weatherDataPersister.all()) {
			if (w.getCity().equals(city)) {
				weatherDataList.add(w);
			}
		}
		return weatherDataList;
	}

	@Override
	public List<WeatherData> findAllWeatherData() throws Exception {
		return weatherDataPersister.all();
	}

	@Override
	public City addCity(City city) throws Exception {
		return cityPersister.add(city);
	}

	@Override
	public City updateCity(City city) throws Exception {
		return cityPersister.update(city);
	}

	@Override
	public boolean deleteCity(City city) throws Exception {
		return cityPersister.delete(city);
	}

	@Override
	public City findCityById(int id) throws Exception {
		return cityPersister.findByID(id);
	}

	@Override
	public City findCityByName(String name) throws Exception {
		return cityPersister.findByName(name);
	}

	@Override
	public List<City> findAllCities() throws Exception {
		return cityPersister.all();
	}

	@Override
	public List<WeatherData> findByDate(Timestamp startDate, Timestamp endDate, int city_id) throws Exception {
		return weatherDataPersister.findByDate(startDate, endDate, city_id);
	}

	@Override
	public List<WeatherData> getLast24HoursData(int city_id) throws Exception {
		return weatherDataPersister.getLast24Hours(city_id);
	}

	@Override
	public Operator addOperator(Operator operator) throws Exception {
		operatorPersister.add(operator);
		return operator;
	}

	@Override
	public Operator updateOperator(Operator operator) throws Exception {
		operatorPersister.update(operator);
		return operator;
	}

	@Override
	public boolean deleteOperator(Operator operator) throws Exception {
		return operatorPersister.delete(operator);
	}

	@Override
	public boolean deleteOperatorByID(int personalNummer) throws Exception {
		return operatorPersister.deleteByID(personalNummer);
	}

	@Override
	public Operator findOperatorByUsername(String username) throws Exception {

		return operatorPersister.findByUsername(username);
	}

	@Override
	public Operator findOperatorByID(int personalNummer) throws Exception {
		return operatorPersister.findByID(personalNummer);
	}

	@Override
	public List<Operator> allOperator() throws Exception {
		return operatorPersister.all();
	}

	@Override
	public Customer addCustomer(Customer customer) throws Exception {
		customerPersister.add(customer);
		return customer;
	}

	@Override
	public Customer updateCustomer(Customer customer) throws Exception {
		customerPersister.update(customer);
		return customer;
	}

	@Override
	public boolean deleteCustomer(Customer customer) throws Exception {
		return customerPersister.delete(customer);
	}

	@Override
	public boolean deleteCustomerByID(int kundenNummer) throws Exception {
		return customerPersister.deleteByID(kundenNummer);
	}

	@Override
	public Customer findCustomerByFirmenName(String firmenName) throws Exception {
		return customerPersister.findByFirmenName(firmenName);
	
	}

	@Override
	public Customer findCustomerByID(int kundenNummer) throws Exception {
		return customerPersister.findByID(kundenNummer);
	}

	@Override
	public List<Customer> allCustomer() throws Exception {
		return customerPersister.all();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		ControlImpl control = (ControlImpl) o;
		return Objects.equals(cityPersister, control.cityPersister) &&
				Objects.equals(weatherDataPersister, control.weatherDataPersister) &&
				Objects.equals(operatorPersister, control.operatorPersister) &&
				Objects.equals(customerPersister, control.customerPersister);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), cityPersister, weatherDataPersister, operatorPersister, customerPersister);
	}

	@Override
	public String toString() {
		return "ControlImpl{" +
				"cityPersister=" + cityPersister +
				", weatherDataPersister=" + weatherDataPersister +
				", operatorPersister=" + operatorPersister +
				", customerPersister=" + customerPersister +
				'}';
	}
}
