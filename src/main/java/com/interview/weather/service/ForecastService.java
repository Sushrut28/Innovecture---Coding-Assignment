package com.interview.weather.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.interview.weather.modal.Forecast;
import com.interview.weather.modal.HourlyForecast;
import com.interview.weather.modal.WeatherForecastWrapper;

@Service
public class ForecastService {

	@Autowired
	private RestTemplate restTemplate;
	Logger logger = LoggerFactory.getLogger(ForecastService.class);

	public WeatherForecastWrapper getForecastByZipCode(String zipCode, String apiKey) {
		WeatherForecastWrapper weatherForeCast = new WeatherForecastWrapper();
		try {
			weatherForeCast = restTemplate.getForObject(
					"https://api.openweathermap.org/data/2.5/forecast?zip=" + zipCode + "&appid=" + apiKey,
					WeatherForecastWrapper.class);
		} catch (Exception ex) {
			weatherForeCast.setMessage(ex.getMessage());
			return weatherForeCast;
		}
		HourlyForecast coolestHour = findCoolestHour(weatherForeCast).stream().findFirst().get();
		WeatherForecastWrapper respone = new WeatherForecastWrapper(weatherForeCast.getCity(),
				Arrays.asList(new Forecast(coolestHour)), weatherForeCast.getMessage());
		return respone;
	}

	private List<HourlyForecast> findCoolestHour(WeatherForecastWrapper weatherForecast) {
		List<Forecast> forecastList = weatherForecast.getList();
		List<Forecast> tomorrowForecastList = new ArrayList<>(forecastList.size());
		Comparator<HourlyForecast> compareByCoolestHour = Comparator.comparing(HourlyForecast::getTemp_min)
				.thenComparing(HourlyForecast::getDate);
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date tomorrow = calendar.getTime();
		String tomorrowDate = dateFormatter.format(tomorrow);

		forecastList.stream().forEach(forecast -> {
			try {
				Date formattedDate = dateFormatter.parse(forecast.getDt_txt());
				String forecastFormattedDate = dateFormatter.format(formattedDate);
				if (tomorrowDate.equals(forecastFormattedDate)) {
					String time = timeFormatter.format(formattedDate);
					forecast.getMain().setCoolestHour(time);
					forecast.getMain().setDate(forecastFormattedDate);
					tomorrowForecastList.add(forecast);
				}
			} catch (ParseException e) {
				logger.debug("Error while parsing the received Date");
			}
		});
		return tomorrowForecastList.stream().map(s -> s.getMain()).sorted(compareByCoolestHour)
				.collect(Collectors.toList());
	}
}
