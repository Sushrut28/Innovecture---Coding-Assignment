package com.interview.weather.modal;

public class HourlyForecast {
	private String temp;
	private String temp_min;
	private String temp_max;
	private String date;
	private String coolestHour;

	public String getCoolestHour() {
		return coolestHour;
	}

	public void setCoolestHour(String coolestHour) {
		this.coolestHour = coolestHour;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTemp_min() {
		return temp_min;
	}

	public void setTemp_min(String temp_min) {
		this.temp_min = temp_min;
	}

	public String getTemp_max() {
		return temp_max;
	}

	public void setTemp_max(String temp_max) {
		this.temp_max = temp_max;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}
}
