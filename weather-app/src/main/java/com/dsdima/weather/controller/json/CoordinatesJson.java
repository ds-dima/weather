package com.dsdima.weather.controller.json;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Created by dsshevchenko on 1/23/18.
 */
public class CoordinatesJson {

    @Max(value = 90, message = "{latitude.range.message}")
    @Min(value = -90, message = "{latitude.range.message}")
    private Integer latitude;

    @Max(value = 180, message = "{longitude.range.message}")
    @Min(value = -180, message = "{longitude.range.message}")
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
