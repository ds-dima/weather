package com.dsdima.weather.controller.json;

/**
 * Created by dsshevchenko on 1/23/18.
 */
public class CoordinatesJson {

    private String latitude;
    private String longitude;

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    /**
     * Set latitude property.
     *
     * @param val - new latitude value.
     * @return the {@link CoordinatesJson} for chaining.
     */
    public CoordinatesJson setLatitude(String val) {
        latitude = val;
        return this;
    }

    /**
     * Set longitude property.
     *
     * @param val - new longitude value.
     * @return the {@link CoordinatesJson} for chaining.
     */
    public CoordinatesJson setLongitude(String val) {
        longitude = val;
        return this;
    }
}
