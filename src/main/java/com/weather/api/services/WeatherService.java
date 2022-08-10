package com.weather.api.services;

import com.weather.api.model.WeatherResponse;
import org.springframework.stereotype.Service;

@Service
public interface WeatherService {
    WeatherResponse getAverageWeatherData(String city);
}
