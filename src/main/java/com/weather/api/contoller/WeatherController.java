package com.weather.api.contoller;

import com.weather.api.exception.util.CityNotFoundException;
import com.weather.api.exception.util.CityRequiredException;
import com.weather.api.model.WeatherResponse;
import com.weather.api.repositories.CityRepository;
import com.weather.api.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    @Autowired
    private WeatherService weatherService;
    @Autowired
    private CityRepository cityRepository;
    @GetMapping(value = "/data", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WeatherResponse> getWeatherResponse(@RequestParam(value = "city",required = true) String city) throws CityRequiredException, CityNotFoundException {
        if(city==null || city.equalsIgnoreCase("")){
            throw  new CityRequiredException();
        }else if(cityRepository.findByName(city)==null){
            throw new CityNotFoundException();
        }

        WeatherResponse weatherResponse=weatherService.getAverageWeatherData(city);
        return new ResponseEntity(weatherResponse, HttpStatus.OK);
    }
}
