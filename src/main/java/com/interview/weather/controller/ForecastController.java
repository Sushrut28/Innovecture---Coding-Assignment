package com.interview.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.interview.weather.modal.WeatherForecastWrapper;
import com.interview.weather.service.ForecastService;

@RestController
@RequestMapping("/forecast")
public class ForecastController {

	@Value("${api.key}")
	private String apiKey;

	@Autowired
	private ForecastService forecastService;

	@GetMapping("")
	public ModelAndView getForecast() {
		ModelAndView model = new ModelAndView();
		model.setViewName("weather-forecast");
		return model;
	}

	@GetMapping("/zipcode")
	public ModelAndView getForecastByZipCode(@RequestParam String zipcode) {
		WeatherForecastWrapper weatherForeCast = forecastService.getForecastByZipCode(zipcode, apiKey);
		ModelAndView model = new ModelAndView();
		model.setViewName("weather-forecast");
		model.addObject("weatherForeCast", weatherForeCast);
		return model;
	}
}
