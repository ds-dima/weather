package com.dsdima.weather.service;

import com.dsdima.weather.model.WeatherInfo;

/**
 * @author dsshevchenko
 * @since <pre>1/19/2018</pre>
 */
public interface WeatherService {

    WeatherInfo getWeatherByCityId(Integer cityId);

    WeatherInfo getWeatherByCityName(String cityName);

    WeatherInfo getWeatherByCoordinates(Integer lat, Integer lon);
}
