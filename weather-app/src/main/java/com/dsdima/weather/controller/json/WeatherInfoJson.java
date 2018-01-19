package com.dsdima.weather.controller.json;

/**
 * Created by dsshevchenko on 1/19/18.
 */
public class WeatherInfoJson {
    private Integer temperature;
    private Integer pressure;
    private Integer windSpeed;
    private String windDirection;

    public Integer getTemperature() {
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
     * @return the {@link WeatherInfoJson} for chaining.
     */
    public WeatherInfoJson setTemperature(Integer val) {
        temperature = val;
        return this;
    }

    /**
     * Set pressure property.
     *
     * @param val - new pressure value.
     * @return the {@link WeatherInfoJson} for chaining.
     */
    public WeatherInfoJson setPressure(Integer val) {
        pressure = val;
        return this;
    }

    /**
     * Set windSpeed property.
     *
     * @param val - new windSpeed value.
     * @return the {@link WeatherInfoJson} for chaining.
     */
    public WeatherInfoJson setWindSpeed(Integer val) {
        windSpeed = val;
        return this;
    }

    /**
     * Set windDirection property.
     *
     * @param val - new windDirection value.
     * @return the {@link WeatherInfoJson} for chaining.
     */
    public WeatherInfoJson setWindDirection(String val) {
        windDirection = val;
        return this;
    }
}
