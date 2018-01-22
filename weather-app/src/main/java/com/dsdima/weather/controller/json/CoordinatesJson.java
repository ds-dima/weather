package com.dsdima.weather.controller.json;

/**
 * Created by dsshevchenko on 1/23/18.
 */
public class CoordinatesJson {
    private Integer latitude;
    private Integer longitude;

    public Integer getLatitude() {
        return latitude;
    }

    public Integer getLongitude() {
        return longitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }
}
