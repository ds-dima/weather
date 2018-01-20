package com.dsdima.weather.client.converter;

import com.dsdima.weather.client.json.WeatherResponse;
import com.dsdima.weather.model.WeatherInfo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by dsshevchenko on 1/19/18.
 */
@Component
public class OpenWeatherResponseConverter implements Converter<WeatherResponse, WeatherInfo> {

    @Override
    public WeatherInfo convert(WeatherResponse response) {
        return new WeatherInfo()
                    .setTemperature(response.getTemperature())
                    .setPressure(response.getPressure())
                    .setWindDirection(response.getWindDirection())
                    .setWindSpeed(response.getWindSpeed());
    }
}
