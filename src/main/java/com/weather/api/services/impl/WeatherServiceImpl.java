package com.weather.api.services.impl;

import com.weather.api.model.Weather;
import com.weather.api.model.WeatherResponse;
import com.weather.api.repositories.WeatherRepository;
import com.weather.api.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService {
    @Autowired
    private WeatherRepository weatherRepository;
    @Override
    public WeatherResponse getAverageWeatherData(String city) {
        List<Weather> weatherList=weatherRepository.getLast2days(city);

        DecimalFormat df = new DecimalFormat("#.##");
        WeatherResponse weatherResponse=new WeatherResponse();
        weatherResponse.setCityName(city);
        double avgDayTemp=weatherList.stream()
                                     .mapToDouble(weather -> Double.valueOf(df.format(weather.getDayTemp())))
                                     .average()
                                     .getAsDouble();
        weatherResponse.setAverageDayTemperature(avgDayTemp);

        double avgnightTemp=weatherList.stream()
                                       .mapToDouble(w->Double.valueOf(df.format(w.getNightTemp())))
                                       .average()
                                       .getAsDouble();
        weatherResponse.setAverageNightTemperature(avgnightTemp);

        double avgPressure=weatherList.stream()
                                      .mapToDouble(w->Double.valueOf(df.format(w.getPressure())))
                                      .average()
                                      .getAsDouble();

        weatherResponse.setAveragePressure(avgPressure);
        return weatherResponse;
    }
}
