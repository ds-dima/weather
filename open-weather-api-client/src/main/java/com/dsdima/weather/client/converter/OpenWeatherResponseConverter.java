package com.dsdima.weather.client.converter;

import com.dsdima.weather.client.json.WeatherResponse;
import com.dsdima.weather.domain.WeatherInfo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Weather response converter
 * @author dsshevchenko
 * @since <pre>1/22/2018</pre>
 */
@Component
public class OpenWeatherResponseConverter implements Converter<WeatherResponse, WeatherInfo> {

    /**
     * Convert open weather api response {@link WeatherResponse} to {@link WeatherInfo}
     * @param response response for convertion
     * @return converted weather info
     */
    @Override
    public WeatherInfo convert(WeatherResponse response) {
        return new WeatherInfo()
                    .setTemperature(response.getTemperature())
                    .setPressure(response.getPressure())
                    .setWindDirection(response.getWindDirection())
                    .setWindSpeed(response.getWindSpeed())
                    .setCityName(response.getCityName());
    }
}
