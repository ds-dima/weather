package com.dsdima.weather.converter;

/**
 * @author dsshevchenko
 * @since <pre>1/19/2018</pre>
 */
public enum WindDirection {

    NORTH(0, 27.5f, "Северный"),
    NORTH_EAST(27.6f, 72.5f, "Северо-восточный"),
    EAST(72.6f, 117.5f, "Восточный"),
    SOUTH_EAST(117.6f, 162.5f, "Юго-восточный"),
    SOUTH(162.6f, 207.5f, "Южный"),
    SOUTH_WEST(207.6f, 252.5f, "Юго-западный"),
    WEST(252.6f, 297.5f, "Западный"),
    NORTH_WEST(297.6f, 342.5f, "Северо-западный");

    private float fromDegree;
    private float toDegree;
    private String description;

    public static WindDirection of(int degree) {
        if (degree <= 360 && degree >= NORTH_WEST.toDegree) {
            return NORTH;
        }
        for (WindDirection item: values()) {
            if (degree >= item.fromDegree && degree <= item.toDegree) {
                return item;
            }
        }
        throw new IllegalArgumentException("Degree can't be out of range 0 - 360");
    }

    WindDirection(float fromDegree, float toDegree, String description) {
        this.fromDegree = fromDegree;
        this.toDegree = toDegree;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Float getFromDegree() {
        return fromDegree;
    }

    public Float getToDegree() {
        return toDegree;
    }
}
