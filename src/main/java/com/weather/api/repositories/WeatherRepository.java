package com.weather.api.repositories;

import com.weather.api.model.Weather;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WeatherRepository extends CrudRepository<Weather,Long> {
    @Query(value = "SELECT *  FROM WEATHER WHERE TIMESTAMPDIFF(DAY, CURRENT_DATE,WEATHER_DATE)>-1 and TIMESTAMPDIFF(DAY, CURRENT_DATE,WEATHER_DATE)<2 and CITY_NAME=:city",nativeQuery = true)
     List<Weather> getLast2days(@Param("city") String city);

}
