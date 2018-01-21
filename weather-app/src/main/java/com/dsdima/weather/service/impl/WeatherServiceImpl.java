package com.dsdima.weather.service.impl;

import com.dsdima.weather.client.service.WeatherApiClient;
import com.dsdima.weather.model.WeatherInfo;
import com.dsdima.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dsshevchenko
 * @since <pre>1/19/2018</pre>
 */
@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private WeatherApiClient weatherApiClient;

    @Override
    public WeatherInfo getWeatherByCityId(String cityId) {
        return weatherApiClient.getWeatherByCityId(cityId);
    }

    @Override
    public WeatherInfo getWeatherByCityName(String cityName) {
        return weatherApiClient.getWeatherByCityName(cityName);
    }

    @Override
    public WeatherInfo getWeatherByCoordinates(String lat, String lon) {
        return weatherApiClient.getWeatherByCoordinates(lat, lon);
    }
}
