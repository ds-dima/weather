package com.dsdima.weather.client.service.impl;

import com.dsdima.weather.client.json.WeatherResponse;
import com.dsdima.weather.client.service.WeatherApiClient;
import com.dsdima.weather.model.WeatherInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
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
/*

    @Autowired
    private ConversionService converter;
*/

    @Override
    public WeatherInfo getWeatherByCityId(String cityId) {
        Map<String, String> params = new HashMap<>();
        params.put("id", cityId);
        params.put("appid", appId);
        params.put("units", "metric");
        String url = apiUri + "/weather?id={id}&appid={appid}&units={units}";
        WeatherResponse response = restTemplate.getForObject(url, WeatherResponse.class, params);
        return new WeatherInfo();//converter.convert(response, WeatherInfo.class);
    }
}
