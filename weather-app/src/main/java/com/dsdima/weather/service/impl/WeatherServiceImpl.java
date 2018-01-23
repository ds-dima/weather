package com.dsdima.weather.service.impl;

import com.dsdima.weather.client.service.WeatherApiClient;
import com.dsdima.weather.model.WeatherInfo;
import com.dsdima.weather.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * @author dsshevchenko
 * @since <pre>1/19/2018</pre>
 */
@Service
public class WeatherServiceImpl implements WeatherService {

    /** Logger */
    private static final Logger LOG = LoggerFactory.getLogger(WeatherServiceImpl.class);

    @Autowired
    private ExecutorService weatherTaskExecutorService;

    @Autowired
    private WeatherApiClient weatherApiClient;

    @Override
    public WeatherInfo getWeatherByCityId(Integer cityId) {
        LOG.info("Request:get weather by city id - {}", cityId);
        return weatherApiClient.getWeatherByCityId(cityId);
    }

    @Override
    public Future<WeatherInfo> getWeatherByCityName(String cityName) {
        return weatherTaskExecutorService.submit(() -> {
            LOG.info("Start task: get weather by city name - {}", cityName);
            return weatherApiClient.getWeatherByCityName(cityName);
        });
    }

    @Override
    public Future<WeatherInfo> getWeatherByCoordinates(Integer lat, Integer lon) {
        return weatherTaskExecutorService.submit(() -> {
            LOG.info("Start task: get weather by coordinates: latitude={}, longitude={}", lat, lon);
            return weatherApiClient.getWeatherByCoordinates(lat, lon);
        });
    }
}
