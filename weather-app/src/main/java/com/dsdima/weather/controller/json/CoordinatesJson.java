package com.dsdima.weather.controller.json;

/**
 * Coordinate json
 *
 * @author dsshevchenko
 * @since <pre>1/22/2018</pre>
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
