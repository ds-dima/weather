package com.dsdima.weather.client.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Open weather api response
 *
 * @author dsshevchenko
 * @since <pre>1/19/2018</pre>
 */
public class WeatherResponse {

    private Float temperature;
    private Integer pressure;
    private Float windSpeed;
    private Integer windDirection;
    private String cityName;

    public Float getTemperature() {
        return temperature;
    }

    public Integer getPressure() {
        return pressure;
    }

    public Float getWindSpeed() {
        return windSpeed;
    }

    public Integer getWindDirection() {
        return windDirection;
    }

    public String getCityName() {
        return cityName;
    }

    @JsonProperty("main")
    public void unpackTemperature(Map<String, String> main) {
        temperature = floatValue(main.get("temp"));
        pressure = intValue(main.get("pressure"));
    }

    @JsonProperty("wind")
    public void unpackWindSpeed(Map<String, String> wind) {
         windSpeed = floatValue(wind.get("speed"));
         windDirection = intValue(wind.get("deg"));
    }

    @JsonProperty("name")
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    private Integer intValue(String val) {
        try {
            return Integer.parseInt(val);
        } catch (Exception e) {
            return null;
        }
    }

    private Float floatValue(String val) {
        try {
            return Float.parseFloat(val);
        } catch (Exception e) {
            return null;
        }
    }

}
