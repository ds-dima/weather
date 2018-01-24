package com.dsdima.weather.client.service.exception;

/**
 * Open weather client api exception
 * Throws then requests fails
 *
 * @author dsshevchenko
 * @since <pre>1/22/2018</pre>
 */
public class OpenWeatherApiException extends RuntimeException {

    public OpenWeatherApiException(String message) {
        super(message);
    }
}
