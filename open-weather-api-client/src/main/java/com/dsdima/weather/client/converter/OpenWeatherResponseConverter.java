package com.dsdima.weather.client.converter;

import com.dsdima.weather.client.json.WeatherResponse;
import com.dsdima.weather.model.WeatherInfo;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by dsshevchenko on 1/19/18.
 */
public class OpenWeatherResponseConverter implements Converter<WeatherResponse, WeatherInfo> {

    @Override
    public WeatherInfo convert(WeatherResponse weatherResponse) {
        return new WeatherInfo();
    }
}
