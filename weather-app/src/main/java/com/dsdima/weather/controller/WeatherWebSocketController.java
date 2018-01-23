package com.dsdima.weather.controller;

import com.dsdima.weather.controller.json.CoordinatesJson;
import com.dsdima.weather.controller.json.WeatherInfoJson;
import com.dsdima.weather.model.WeatherInfo;
import com.dsdima.weather.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.concurrent.ExecutionException;

import static java.lang.String.format;

/**
 * Created by dsshevchenko on 1/23/18.
 */
@SuppressWarnings("unused")
@Controller
@Validated
public class WeatherWebSocketController {

    /** Logger */
    private static final Logger LOG = LoggerFactory.getLogger(WeatherWebSocketController.class);

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private ConversionService conversionService;

    @MessageMapping("/{clientToken}/weather-by-city-name")
    public void getWeatherByCityName(@DestinationVariable("clientToken") String clientToken, String cityName) throws Throwable {
        LOG.info("Request:get weather by city name - {}", cityName);
        try {
            WeatherInfo weather = weatherService.getWeatherByCityName(cityName).get();
            WeatherInfoJson weatherJson = conversionService.convert(weather, WeatherInfoJson.class);
            messagingTemplate.convertAndSend(format("/topic/%s/result", clientToken), weatherJson);
            LOG.info("Response:weather by city name - {}, result - {}", cityName, weatherJson);
        } catch (ExecutionException e) {
            throw e.getCause();
        }

    }

    @MessageMapping("/{clientToken}/weather-by-coordinates")
    public void getWeatherByCoordinates(@DestinationVariable("clientToken") String clientToken, @Valid CoordinatesJson coordinates) throws Exception {
        LOG.info("Request:get weather by coordinates: latitude={}, longitude={}",
                coordinates.getLatitude(), coordinates.getLongitude());
        WeatherInfo weather = weatherService.getWeatherByCoordinates(coordinates.getLatitude(), coordinates.getLongitude()).get();
        WeatherInfoJson weatherJson = conversionService.convert(weather, WeatherInfoJson.class);
        messagingTemplate.convertAndSend(format("/topic/%s/result", clientToken), weatherJson);
        LOG.info("Response:weather by coordinates: latitude={}, longitude={}, result - {}",
                coordinates.getLatitude(), coordinates.getLongitude(), weatherJson);
    }

}
