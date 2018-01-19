package com.dsdima.weather.controller;

import com.dsdima.weather.controller.json.WeatherInfoJson;
import com.dsdima.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by dsshevchenko on 1/19/18.
 */
@Controller
@RequestMapping("/api/v1.0/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private ConversionService conversionService;

    @RequestMapping(value = "/by-city-id", method = RequestMethod.GET)
    @ResponseBody
    public WeatherInfoJson getInfoByCityId(@RequestParam String cityId) {
        return conversionService.convert(weatherService.getWeatherByCityId(cityId), WeatherInfoJson.class);
    }

    @RequestMapping(value = "/by-city-name", method = RequestMethod.GET)
    @ResponseBody
    public WeatherInfoJson getInfoByCityName(@RequestParam String cityName) {
        return conversionService.convert(weatherService.getWeatherByCityName(cityName), WeatherInfoJson.class);
    }

    @RequestMapping(value = "/by-coordinates", method = RequestMethod.GET)
    @ResponseBody
    public WeatherInfoJson getInfoByCityCoordinates(@RequestParam String lat, @RequestParam String lon) {
        return conversionService.convert(weatherService.getWeatherByCoordinates(lat, lon), WeatherInfoJson.class);
    }

}
