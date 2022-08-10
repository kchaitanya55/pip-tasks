package com.weather.api.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.api.model.City;
import com.weather.api.model.Weather;
import com.weather.api.repositories.CityRepository;
import com.weather.api.repositories.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InitialDataSetUp {
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private RestTemplate restTemplate;


    private static String appID="28a16991701dbe094e91e03c1ddbdfbd";

    @EventListener(ApplicationReadyEvent.class)
    public void init() throws IOException {
        List<City> cityList= (List<City>) cityRepository.findAll();

        for (City city:cityList){
            String openWeatherAppURL="https://api.openweathermap.org/data/2.5/onecall?lat=#latitude#&lon=#longitude#&appid="+appID;
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity = new HttpEntity<String>(headers);
            openWeatherAppURL=openWeatherAppURL.replace("#latitude#",city.getLatitude()).replace("#longitude#",city.getLongitude());
            System.out.println("Fetching Data for the city->"+city.getName()+" and URL is ->"+openWeatherAppURL);
            String data=restTemplate.exchange(openWeatherAppURL, HttpMethod.GET, entity, String.class).getBody();
            Map<String,List<Map<String, Object>>> map = new HashMap<>();
            ObjectMapper mapper = new ObjectMapper();
            map = mapper.readValue(data, HashMap.class);
            List<Map<String, Object>> dailyData=map.get("daily");
            for (Map<String, Object> daily:dailyData){
                Weather weather=new Weather();
                weather.setCityId(city.getId());
                weather.setCityName(city.getName());
                long timeStamp= ((Number) daily.get("dt")).longValue();
                weather.setWeatherDate(new Timestamp(timeStamp*1000));
                Map<String,Object> tempData= (Map<String, Object>) daily.get("temp");
                double dayTemp= ((Number) tempData.get("day")).doubleValue();
                double nightTemp=((Number) tempData.get("night")).doubleValue();
                weather.setDayTemp(dayTemp-273.15); //Kelvin to Celsius
                weather.setNightTemp(nightTemp-273.15);
                weather.setPressure(((Number) daily.get("pressure")).doubleValue());
                weatherRepository.save(weather);
            }

        }
        System.out.println("application ready to Use");
    }
}
