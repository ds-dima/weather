package com.dsdima.weather.client.service.impl;

import com.dsdima.weather.client.service.WeatherApiClient;
import com.dsdima.weather.model.WeatherInfo;
import org.springframework.stereotype.Service;

/**
 * @author dsshevchenko
 * @since <pre>1/19/2018</pre>
 */
@Service
public class OpenWeatherApiClient implements WeatherApiClient {

    @Override
    public WeatherInfo getWeatherByCityId(String cityId) {
        return null;
    }
}
