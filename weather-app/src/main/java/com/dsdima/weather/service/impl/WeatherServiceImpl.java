package com.dsdima.weather.service.impl;

import com.dsdima.weather.client.service.WeatherApiClient;
import com.dsdima.weather.model.WeatherInfo;
import com.dsdima.weather.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

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
    public WeatherInfo getWeatherByCityName(String cityName) throws Throwable {
        LOG.info("Request:get weather by city name - {}", cityName);
        try {
            WeatherInfo weatherInfo = weatherTaskExecutorService.submit(() -> {
                LOG.info("Start task: get weather by city name - {}", cityName);
                return weatherApiClient.getWeatherByCityName(cityName);
            }).get();
            LOG.info("Response:weather by city name - {}, result - {}", cityName, weatherInfo);
            return weatherInfo;
        } catch (ExecutionException e) {
            throw e.getCause();
        }
    }

    @Override
    public WeatherInfo getWeatherByCoordinates(Integer lat, Integer lon) throws Throwable {
        LOG.info("Request:get weather by coordinates: latitude={}, longitude={}", lat, lon);
        try {
            WeatherInfo weatherInfo = weatherTaskExecutorService.submit(() -> {
                LOG.info("Start task: get weather by coordinates: latitude={}, longitude={}", lat, lon);
                return weatherApiClient.getWeatherByCoordinates(lat, lon);
            }).get();
            LOG.info("Response:weather by coordinates: latitude={}, longitude={}, result - {}",
                    lat, lon, weatherInfo);
            return weatherInfo;
        } catch (ExecutionException e) {
            throw e.getCause();
        }
    }
}
