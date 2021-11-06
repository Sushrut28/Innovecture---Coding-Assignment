package com.interview.weather.modal;

import java.util.List;

public class WeatherForecastWrapper {

	private City city;
	private List<Forecast> list;
	private String message;

	public WeatherForecastWrapper() {

	}

	public WeatherForecastWrapper(String message) {
		super();
		this.message = message;
	}

	public WeatherForecastWrapper(City city, List<Forecast> list, String message) {
		super();
		this.city = city;
		this.list = list;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public List<Forecast> getList() {
		return list;
	}

	public void setList(List<Forecast> list) {
		this.list = list;
	}

}
