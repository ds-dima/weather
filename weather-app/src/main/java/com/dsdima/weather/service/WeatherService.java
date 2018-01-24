package com.dsdima.weather.service;

import com.dsdima.weather.exception.WeatherException;
import com.dsdima.weather.model.WeatherInfo;

import java.security.Principal;

/**
 * @author dsshevchenko
 * @since <pre>1/19/2018</pre>
 */
public interface WeatherService {

    WeatherInfo getWeatherByCityName(String cityName, Principal principal) throws WeatherException;

    WeatherInfo getWeatherByCoordinates(String lat, String lon, Principal principal) throws WeatherException;
}
