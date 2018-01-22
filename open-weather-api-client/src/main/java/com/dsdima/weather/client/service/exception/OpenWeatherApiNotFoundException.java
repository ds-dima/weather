package com.dsdima.weather.client.service.exception;

/**
 * @author dsshevchenko
 * @since <pre>1/22/2018</pre>
 */
public class OpenWeatherApiNotFoundException extends RuntimeException {

    public OpenWeatherApiNotFoundException(String message) {
        super(message);
    }
}
