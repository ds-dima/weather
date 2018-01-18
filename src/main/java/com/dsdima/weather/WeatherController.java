package com.dsdima.weather;

import com.dsdima.weather.json.WeatherInfoJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by dsshevchenko on 1/19/18.
 */
@Controller
@RequestMapping("/api/v1.0/weather")
public class WeatherController {

    @RequestMapping(method = RequestMethod.GET)
    public WeatherInfoJson getInfoByCityId(@RequestParam Integer cityId) {
        return new WeatherInfoJson();
    }

    @RequestMapping(method = RequestMethod.GET)
    public WeatherInfoJson getInfoByCityName(@RequestParam String cityName) {
        return new WeatherInfoJson();
    }

    @RequestMapping(method = RequestMethod.GET)
    public WeatherInfoJson getInfoByCityCoordinates(@RequestParam String lat, @RequestParam String lon) {
        return new WeatherInfoJson();
    }

}
