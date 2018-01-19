package com.dsdima.weather.client.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * @author dsshevchenko
 * @since <pre>1/19/2018</pre>
 */
//@JsonDeserialize(using = WeatherResponseDeserializer.class)
public class WeatherResponse {

    private Float temperature;
    private Integer pressure;
    private Integer windSpeed;
    private String windDirection;


    public Float getTemperature() {
        return temperature;
    }

    public Integer getPressure() {
        return pressure;
    }

    public Integer getWindSpeed() {
        return windSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }

    /**
     * Set temperature property.
     *
     * @param val - new temperature value.
     * @return the {@link WeatherResponse} for chaining.
     */
    public WeatherResponse setTemperature(Float val) {
        temperature = val;
        return this;
    }

    @JsonProperty("main")
    public void unpackTemperature(Map<String, String> main) {
        try {
            temperature = Float.parseFloat(main.get("temp"));
        } catch (NumberFormatException e) {
            temperature = null;
        }

    }

    /**
     * Set pressure property.
     *
     * @param val - new pressure value.
     * @return the {@link WeatherResponse} for chaining.
     */
    public WeatherResponse setPressure(Integer val) {
        pressure = val;
        return this;
    }

    /**
     * Set windSpeed property.
     *
     * @param val - new windSpeed value.
     * @return the {@link WeatherResponse} for chaining.
     */
    public WeatherResponse setWindSpeed(Integer val) {
        windSpeed = val;
        return this;
    }

    /**
     * Set windDirection property.
     *
     * @param val - new windDirection value.
     * @return the {@link WeatherResponse} for chaining.
     */
    public WeatherResponse setWindDirection(String val) {
        windDirection = val;
        return this;
    }

}
