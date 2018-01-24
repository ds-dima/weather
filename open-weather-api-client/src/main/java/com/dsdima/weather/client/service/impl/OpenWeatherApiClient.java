package com.dsdima.weather.client.service.impl;

import com.dsdima.weather.client.converter.OpenWeatherResponseConverter;
import com.dsdima.weather.client.json.WeatherResponse;
import com.dsdima.weather.client.service.WeatherApiClient;
import com.dsdima.weather.domain.WeatherInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Open weather api client
 * Main method for client interaction
 * @author dsshevchenko
 * @since <pre>1/19/2018</pre>
 */
@Service
public class OpenWeatherApiClient implements WeatherApiClient {

    @Value("${openweather.api.uri}")
    private String apiUri;

    @Value("${openweather.api.appid}")
    private String appId;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OpenWeatherResponseConverter converter;

    @Override
    public WeatherInfo getWeatherByCityId(final Integer cityId) {
        Map<String, String> params = new HashMap<>();
        params.put("id", "" + cityId);
        return converter.convert(getWeatherResponse(params));
    }

    @Override
    public WeatherInfo getWeatherByCityName(final String cityName) {
        Map<String, String> params = new HashMap<>();
        params.put("q", cityName);
        return converter.convert(getWeatherResponse(params));
    }

    @Override
    public WeatherInfo getWeatherByCoordinates(final String lat, String lon) {
        Map<String, String> params = new HashMap<>();
        params.put("lat", lat);
        params.put("lon", lon);
        return converter.convert(getWeatherResponse(params));
    }

    private WeatherResponse getWeatherResponse(final Map<String, String> params) {
        UriComponentsBuilder paramBuilder = UriComponentsBuilder
                .fromUriString(apiUri + "/weather")
                .queryParam("appid", appId)
                .queryParam("units", "metric");
        params.entrySet()
                .forEach(entry -> paramBuilder.queryParam(entry.getKey(), entry.getValue()));
        return restTemplate.getForObject(paramBuilder.build(false).toUriString(), WeatherResponse.class, params);
    }
}
