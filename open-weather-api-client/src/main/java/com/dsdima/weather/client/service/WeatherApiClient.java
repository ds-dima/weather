package com.dsdima.weather.client.service;


import com.dsdima.weather.model.WeatherInfo;

/**
 * @author dsshevchenko
 * @since <pre>1/19/2018</pre>
 */
public interface WeatherApiClient {

    WeatherInfo getWeatherByCityId(String cityId);
}
