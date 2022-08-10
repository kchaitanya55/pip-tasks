package com.weather.api.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.springframework.stereotype.Component;

@Component
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class WeatherResponse {
    private String cityName;
    private double averageDayTemperature;
    private double averageNightTemperature;
    private double averagePressure;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public double getAverageDayTemperature() {
        return averageDayTemperature;
    }

    public void setAverageDayTemperature(double averageDayTemperature) {
        this.averageDayTemperature = averageDayTemperature;
    }

    public double getAverageNightTemperature() {
        return averageNightTemperature;
    }

    public void setAverageNightTemperature(double averageNightTemperature) {
        this.averageNightTemperature = averageNightTemperature;
    }

    public double getAveragePressure() {
        return averagePressure;
    }

    public void setAveragePressure(double averagePressure) {
        this.averagePressure = averagePressure;
    }
}
