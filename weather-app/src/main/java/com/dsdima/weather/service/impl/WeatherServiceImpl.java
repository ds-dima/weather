package com.dsdima.weather.service.impl;

import com.dsdima.weather.client.service.WeatherApiClient;
import com.dsdima.weather.exception.WeatherException;
import com.dsdima.weather.domain.WeatherInfo;
import com.dsdima.weather.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

import static java.lang.String.format;

/**
 * Weather service
 *
 * @author dsshevchenko
 * @since <pre>1/19/2018</pre>
 */
@Service
public class WeatherServiceImpl implements WeatherService {

    /** Logger */
    private static final Logger LOG = LoggerFactory.getLogger(WeatherServiceImpl.class);

    /**
     * Executor service
     */
    @Autowired
    private ExecutorService weatherTaskExecutorService;

    /**
     * Weather api ckient
     */
    @Autowired
    private WeatherApiClient weatherApiClient;

    /**
     * Get weather by city name
     * Execute one request per time
     * @param cityName city name
     * @param principal user principal
     * @return weather info
     * @throws WeatherException if weather has provider errors
     */
    @Override
    public WeatherInfo getWeatherByCityName(String cityName, Principal principal) throws WeatherException {
        String requestName = format("get weather by city name(%s)", cityName);
        return getWeather(() -> {
            LOG.info("User({}):Start task({})", principal.getName(), requestName);
            return weatherApiClient.getWeatherByCityName(cityName);
        }, principal, requestName);
    }

    /**
     * Get weather by coordinates
     * Execute one request per time
     * @param lat latitude
     * @param lon longitude
     * @param principal user principal
     * @return weather info
     * @throws WeatherException if weather has provider errors
     */
    @Override
    public WeatherInfo getWeatherByCoordinates(String lat, String lon, Principal principal) throws WeatherException {
        String requestName = format("get weather by coordinates: latitude=%s, longitude=%s", lat, lon);
        return getWeather(() -> {
            LOG.info("User({}):Start task:({}) " + requestName, principal.getName());
            return weatherApiClient.getWeatherByCoordinates(lat, lon);
        }, principal, requestName);
    }

    private WeatherInfo getWeather(Callable<WeatherInfo> task, Principal principal, String requestName) throws WeatherException {
        LOG.info("User({}):Request({})", principal.getName(), requestName);
        try {
            WeatherInfo weatherInfo = weatherTaskExecutorService.submit(task).get();
            LOG.info("User({}):Response({}), result - {}", principal.getName(), requestName, weatherInfo);
            return weatherInfo;
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            LOG.error("User({}):Error: {}", principal.getName(), cause.getMessage());
            throw new WeatherException(cause.getMessage());
        } catch (InterruptedException e) {
            LOG.error("System error", e);
            throw new WeatherException("Ошибка работы системы, попробуйте выполнить запрос позднее");
        }
    }
}
