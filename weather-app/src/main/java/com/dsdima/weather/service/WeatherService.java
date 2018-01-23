package com.dsdima.weather.service;

import com.dsdima.weather.model.WeatherInfo;

/**
 * @author dsshevchenko
 * @since <pre>1/19/2018</pre>
 */
public interface WeatherService {

    WeatherInfo getWeatherByCityName(String cityName) throws Throwable;

    WeatherInfo getWeatherByCoordinates(Integer lat, Integer lon) throws Throwable;
}
