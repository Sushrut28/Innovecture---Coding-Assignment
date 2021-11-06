package com.interview.weather;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.interview.weather.modal.WeatherForecastWrapper;
import com.interview.weather.service.ForecastService;

@SpringBootTest
public class ForecastServiceTest {

	@Autowired
	ForecastService service;

	@Test
	@DisplayName("Invalid API key")
	void testGetForecastByZipcode() {
		WeatherForecastWrapper response = service.getForecastByZipCode("67301", null);
		Assertions.assertEquals(
				"401 Unauthorized: \"{\"cod\":401, \"message\": \"Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.\"}\"",
				response.getMessage());
	}

	@Test
	@DisplayName("Invalid Zip key")
	void testGetForecastByInvalidZipcode() {
		WeatherForecastWrapper response = service.getForecastByZipCode("411045", "7c61c4aa8a00882a2ac3e4f11ac4a50b");
		Assertions.assertEquals("404 Not Found: \"{\"cod\":\"404\",\"message\":\"city not found\"}\"",
				response.getMessage());
	}

	@Test
	@DisplayName("Valid API key")
	void testGetForecastByZipcodeValiKey() {
		WeatherForecastWrapper response = service.getForecastByZipCode("67301", "7c61c4aa8a00882a2ac3e4f11ac4a50b");
		Assertions.assertNotNull(response.getCity());
		Assertions.assertEquals(response.getCity().getCountry(), "US");
		Assertions.assertEquals(response.getCity().getName(), "Independence");
		Assertions.assertNotNull(response.getList().get(0));
		Assertions.assertNotNull(response.getList().get(0).getMain());
		Assertions.assertNotNull(response.getList().get(0).getMain().getCoolestHour());
	}
}
