package com.interview.weather.modal;

public class Forecast {
	private String dt;
	private String dt_txt;
	private HourlyForecast main;

	public Forecast() {
	}

	public Forecast(HourlyForecast main) {
		super();
		this.main = main;
	}

	public String getDt_txt() {
		return dt_txt;
	}

	public void setDt_txt(String dt_txt) {
		this.dt_txt = dt_txt;
	}

	public HourlyForecast getMain() {
		return main;
	}

	public void setMain(HourlyForecast main) {
		this.main = main;
	}

	public String getDt() {
		return dt;
	}

	public void setDt(String dt) {
		this.dt = dt;
	}
}
