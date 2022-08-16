# WEATHER API

## Overview
The API is useful to fetch weather forecast of following 2 days of a selected city.

## Guidelines for Developer

1. Clone this Project

2. Create an Account in OpenWeatherMap.(https://openweathermap.org/guide)

3. After Account is created go to Profile-> My API keys and copy the key.

4. Replace the key in the below code of File : com.weather.api.configuration.InitialDataSetUp
```java
	private static String appID="";
```
5. Run the Application as Java Application.

## Guidelines for User

1.Hit the API using the path "/data" and valid city name. User will be able to see the average day temperature of following 2 days and average night temperature of following 2 days.See the below Example

```java
	/data?city=delhi
```

2.If the user send the city which is not available .Error response will be sent to the user.
