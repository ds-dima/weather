package com.dsdima.weather.controller;

import com.dsdima.weather.controller.json.CoordinatesJson;
import com.dsdima.weather.controller.json.WeatherInfoJson;
import com.dsdima.weather.exception.WeatherException;
import com.dsdima.weather.model.WeatherInfo;
import com.dsdima.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

import static java.lang.String.format;

/**
 * Created by dsshevchenko on 1/23/18.
 */
@SuppressWarnings("unused")
@Controller
public class WeatherWebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private ConversionService conversionService;

    @MessageMapping("/{clientToken}/weather-by-city-name")
    public void getWeatherByCityName(@DestinationVariable("clientToken") String clientToken, String cityName, Principal principal) {
        WeatherInfoJson weatherJson;
        try {
            WeatherInfo weather = weatherService.getWeatherByCityName(cityName, principal);
            weatherJson = conversionService.convert(weather, WeatherInfoJson.class);
        } catch (WeatherException e) {
            weatherJson = new WeatherInfoJson().setError(e.getMessage());
        }
        messagingTemplate.convertAndSend(format("/topic/%s/result", clientToken), weatherJson);
    }

    @MessageMapping("/{clientToken}/weather-by-coordinates")
    public void getWeatherByCoordinates(@DestinationVariable("clientToken") String clientToken,
                                        CoordinatesJson coordinates,
                                        Principal principal)  {
        WeatherInfoJson weatherJson;
        try {
            WeatherInfo weather = weatherService.getWeatherByCoordinates(coordinates.getLatitude(),
                    coordinates.getLongitude(), principal);
            weatherJson = conversionService.convert(weather, WeatherInfoJson.class);
        } catch (WeatherException e) {
            weatherJson = new WeatherInfoJson().setError(e.getMessage());
        }

        messagingTemplate.convertAndSend(format("/topic/%s/result", clientToken), weatherJson);
    }

}
