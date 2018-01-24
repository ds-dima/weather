package com.dsdima.weather.exception;

/**
 * Weather exception wrapper over client exception
 *
 * @author dsshevchenko
 * @since <pre>1/22/2018</pre>
 */
public class WeatherException extends Exception {

    public WeatherException(String message) {
        super(message);
    }
}
