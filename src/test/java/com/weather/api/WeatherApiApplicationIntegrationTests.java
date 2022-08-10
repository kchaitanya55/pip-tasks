package com.weather.api;

import com.weather.api.contoller.WeatherController;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
class WeatherApiApplicationIntegrationTests {
	private MockMvc mockMvc;

	@Autowired
	WeatherController weatherController;

	@Test
	void getAverageDatapositive() throws Exception {
		mockMvc= MockMvcBuilders.standaloneSetup(weatherController).build();
		String city="mumbai";
		mockMvc.perform( MockMvcRequestBuilders
						.get("/data?city="+city)
						.accept(MediaType.APPLICATION_JSON))
						.andDo(print())
						.andExpect(status().isOk())
						.andExpect(jsonPath("$.city_name",is(city)));
	}

}
