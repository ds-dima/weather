package com.dsdima.weather.service.impl;

import com.dsdima.weather.model.WeatherInfo;
import com.dsdima.weather.service.WeatherService;
import org.springframework.stereotype.Service;

/**
 * @author dsshevchenko
 * @since <pre>1/19/2018</pre>
 */
@Service
public class WeatherServiceImpl implements WeatherService {

    @Override
    public WeatherInfo getWeatherByCityId(String cityId) {
        return new WeatherInfo();
    }

    @Override
    public WeatherInfo getWeatherByCityName(String cityName) {
        return new WeatherInfo();
    }

    @Override
    public WeatherInfo getWeatherByCoordinates(String lat, String lon) {
        return new WeatherInfo();
    }
}
