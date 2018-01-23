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

    /**
     * Set latitude property.
     *
     * @param val - new latitude value.
     * @return the {@link CoordinatesJson} for chaining.
     */
    public CoordinatesJson setLatitude(Integer val) {
        latitude = val;
        return this;
    }

    /**
     * Set latitude property.
     *
     * @param val - new longitude value.
     * @return the {@link CoordinatesJson} for chaining.
     */
    public CoordinatesJson setLongitude(Integer val) {
        longitude = val;
        return this;
    }

}
