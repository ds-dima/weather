package com.dsdima.weather.controller;

import com.dsdima.weather.client.service.WeatherApiClient;
import com.dsdima.weather.controller.json.CoordinatesJson;
import com.dsdima.weather.controller.json.WeatherInfoJson;
import com.dsdima.weather.converter.WindDirection;
import com.dsdima.weather.model.WeatherInfo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;

import java.lang.reflect.Type;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by dsshevchenko on 1/19/18.
 */
public class WeatherWebSocketControllerTest extends AbstractControllerTest {

    @MockBean
    private WeatherApiClient weatherApiClient;

    protected BlockingQueue<WeatherInfoJson> blockingQueue;

    @Before
    public void setup() throws Exception {
        super.setUp();
        blockingQueue = new LinkedBlockingDeque<>();
    }

    @Test
    public void getWeatherByCityNameSuccessTest() throws Exception {
        when(weatherApiClient.getWeatherByCityName(anyString()))
                .thenReturn(new WeatherInfo()
                        .setTemperature(20f)
                        .setPressure(1021)
                        .setWindSpeed(20.5f)
                        .setWindDirection(350)
                        .setCityName("Omsk")
                );

        session.subscribe("/topic/userId/result", new DefaultStompFrameHandler());
        session.send("/app/userId/weather-by-city-name", "Omsk");
        WeatherInfoJson json = blockingQueue.poll(5, SECONDS);
        assertEquals((Integer) 20, json.getTemperature());
        assertEquals((Integer)765, json.getPressure());
        assertEquals((Integer)20, json.getWindSpeed());
        assertEquals(WindDirection.NORTH.getDescription(), json.getWindDirection());
        assertEquals("Omsk", json.getCityName());
    }


    @Test
    public void getWeatherByCoordinatesSuccessTest() throws Exception {
        when(weatherApiClient.getWeatherByCoordinates(anyInt()  , anyInt()))
                .thenReturn(new WeatherInfo()
                        .setTemperature(-35f)
                        .setWindDirection(300)
                        .setCityName("Санкт-Петербург")
                );

        session.subscribe("/topic/userId/result", new DefaultStompFrameHandler());
        session.send("/app/userId/weather-by-coordinates",
                new CoordinatesJson().setLatitude(10).setLongitude(20));
        WeatherInfoJson json = blockingQueue.poll(5, SECONDS);
        assertEquals(new Integer(-35), json.getTemperature());
        assertNull(json.getPressure());
        assertNull(json.getWindSpeed());
        assertEquals(WindDirection.NORTH_WEST.getDescription(), json.getWindDirection());
        assertEquals("Санкт-Петербург", json.getCityName());
    }


    private class DefaultStompFrameHandler implements StompFrameHandler {
        @Override
        public Type getPayloadType(StompHeaders stompHeaders) {
            return WeatherInfoJson.class;
        }

        @Override
        public void handleFrame(StompHeaders stompHeaders, Object o) {
            blockingQueue.offer((WeatherInfoJson) o);
        }
    }
}
