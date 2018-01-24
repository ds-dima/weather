package com.dsdima.weather.client.service.exception;

/**
 * Open weather client api exception
 * Throws then no result found for request search
 *
 * @author dsshevchenko
 * @since <pre>1/22/2018</pre>
 */
public class OpenWeatherApiNotFoundException extends RuntimeException {

    public OpenWeatherApiNotFoundException(String message) {
        super(message);
    }
}
