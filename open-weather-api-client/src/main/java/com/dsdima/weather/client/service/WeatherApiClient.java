package com.dsdima.weather.client.service;


import com.dsdima.weather.domain.WeatherInfo;

/**
 * @author dsshevchenko
 * @since <pre>1/19/2018</pre>
 */
public interface WeatherApiClient {

    /**
     * Get weather by city id
     * @param cityId city id
     * @return weather info
     */
    WeatherInfo getWeatherByCityId(Integer cityId);

    /**
     * Get weather by cityName
     * @param cityName city name
     * @return weather info
     */
    WeatherInfo getWeatherByCityName(String cityName);

    /**
     * Get weather by coordinates
     * @param lat latitude
     * @param lon longitude
     * @return weather info
     */
    WeatherInfo getWeatherByCoordinates(String lat, String lon);
}
