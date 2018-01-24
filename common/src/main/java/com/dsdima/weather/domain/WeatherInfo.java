package com.dsdima.weather.domain;

/**
 * Weather
 * @author dsshevchenko
 * @since <pre>1/19/2018</pre>
 */
public class WeatherInfo {

    private Integer windDirection;
    private Float temperature;
    private Integer pressure;
    private Float windSpeed;
    private String cityName;

    public Integer getWindDirection() {
        return windDirection;
    }

    public Float getTemperature() {
        return temperature;
    }

    public Integer getPressure() {
        return pressure;
    }

    public Float getWindSpeed() {
        return windSpeed;
    }

    public String getCityName() {
        return cityName;
    }

    /**
     * Set windDirection property.
     *
     * @param val - new windDirection value.
     * @return the {@link WeatherInfo} for chaining.
     */
    public WeatherInfo setWindDirection(Integer val) {
        windDirection = val;
        return this;
    }

    /**
     * Set temperature property.
     *
     * @param val - new temperature value.
     * @return the {@link WeatherInfo} for chaining.
     */
    public WeatherInfo setTemperature(Float val) {
        temperature = val;
        return this;
    }

    /**
     * Set pressure property.
     *
     * @param val - new pressure value.
     * @return the {@link WeatherInfo} for chaining.
     */
    public WeatherInfo setPressure(Integer val) {
        pressure = val;
        return this;
    }

    /**
     * Set windSpeed property.
     *
     * @param val - new windSpeed value.
     * @return the {@link WeatherInfo} for chaining.
     */
    public WeatherInfo setWindSpeed(Float val) {
        windSpeed = val;
        return this;
    }

    /**
     * Set windSpeed property.
     *
     * @param val - new cityName value.
     * @return the {@link WeatherInfo} for chaining.
     */
    public WeatherInfo setCityName(String val) {
        cityName = val;
        return this;
    }

    @Override
    public String toString() {
        return "WeatherInfo{" +
                "windDirection=" + windDirection +
                ", temperature=" + temperature +
                ", pressure=" + pressure +
                ", windSpeed=" + windSpeed +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}
