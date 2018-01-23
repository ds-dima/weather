package com.dsdima.weather.service;

import com.dsdima.weather.model.WeatherInfo;

import java.util.concurrent.Future;

/**
 * @author dsshevchenko
 * @since <pre>1/19/2018</pre>
 */
public interface WeatherService {

    WeatherInfo getWeatherByCityId(Integer cityId);

    Future<WeatherInfo> getWeatherByCityName(String cityName);

    Future<WeatherInfo> getWeatherByCoordinates(Integer lat, Integer lon);
}
