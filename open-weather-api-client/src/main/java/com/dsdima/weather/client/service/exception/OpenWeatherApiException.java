package com.dsdima.weather.client.service.exception;

/**
 * @author dsshevchenko
 * @since <pre>1/22/2018</pre>
 */
public class OpenWeatherApiException extends RuntimeException {

    public OpenWeatherApiException(String message) {
        super(message);
    }
}
