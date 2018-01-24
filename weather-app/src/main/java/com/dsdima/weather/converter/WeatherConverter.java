package com.dsdima.weather.converter;

import com.dsdima.weather.controller.json.WeatherInfoJson;
import com.dsdima.weather.domain.WeatherInfo;
import org.springframework.core.convert.converter.Converter;

import static java.util.Objects.nonNull;

/**
 * Converter
 * @author dsshevchenko
 * @since <pre>1/19/2018</pre>
 */
public class WeatherConverter implements Converter<WeatherInfo, WeatherInfoJson> {

    /**
     * Koef transfer hpa to mm hg
     */
    private static final float HPA_TO_MMHG_FACTOR = 0.75006375541921f;

    /**
     * Convert {@link WeatherInfo} to {@link WeatherInfoJson}
     * @param weatherInfo weather info
     * @return json object
     */
    @Override
    public WeatherInfoJson convert(WeatherInfo weatherInfo) {
        return new WeatherInfoJson()
                    .setWindDirection(nonNull(weatherInfo.getWindDirection()) ?
                            WindDirection.of(weatherInfo.getWindDirection()).getDescription() : null)
                    .setTemperature(nonNull(weatherInfo.getTemperature()) ?
                            weatherInfo.getTemperature().intValue() : null)
                    .setPressure(nonNull(weatherInfo.getPressure()) ?
                            ((Float)(HPA_TO_MMHG_FACTOR * weatherInfo.getPressure())).intValue() : null)
                    .setWindSpeed(nonNull(weatherInfo.getWindSpeed()) ?
                            weatherInfo.getWindSpeed().intValue() : null)
                    .setCityName(weatherInfo.getCityName());
    }
}
