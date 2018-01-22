package com.dsdima.weather.controller;

import com.dsdima.weather.controller.json.CoordinatesJson;
import com.dsdima.weather.controller.json.WeatherInfoJson;
import com.dsdima.weather.model.WeatherInfo;
import com.dsdima.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * Created by dsshevchenko on 1/23/18.
 */
@Controller
public class WeatherWebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private ConversionService conversionService;

    @MessageMapping("/weather-by-city-name")
    public void getWeatherByCityName(String cityName) throws Exception {
        WeatherInfo weather = weatherService.getWeatherByCityName(cityName);
        WeatherInfoJson weatherJson = conversionService.convert(weather, WeatherInfoJson.class);
        messagingTemplate.convertAndSend("/topic/result", weatherJson);
    }

    @MessageMapping("/weather-by-coordinates")
    public void getWeatherByCoordinates(CoordinatesJson coordinates) throws Exception {
        WeatherInfo weather = weatherService.getWeatherByCoordinates(coordinates.getLatitude(), coordinates.getLongitude());
        WeatherInfoJson weatherJson = conversionService.convert(weather, WeatherInfoJson.class);
        messagingTemplate.convertAndSend("/topic/result", weatherJson);
    }

}
