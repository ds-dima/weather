package com.dsdima.weather.controller;

import com.dsdima.weather.client.service.WeatherApiClient;
import com.dsdima.weather.controller.json.CoordinatesJson;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertTrue;

/**
 * @author dsshevchenko
 * @since <pre>1/22/2018</pre>
 */
public class WeatherControllerExceptionTest extends AbstractControllerTest {

    @MockBean
    private WeatherApiClient weatherApiClient;

    private BlockingDeque<String[]> errorQueue;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        errorQueue = new LinkedBlockingDeque<>();
    }

    @Test
    public void wrongCoordinatesValuesFailedTest() throws Exception {
        session.subscribe("/topic/userId/error", new StringStompFrameHandler());
        session.send("/app/userId/weather-by-coordinates",
                new CoordinatesJson().setLatitude(91).setLongitude(-181));
        String[] errors = errorQueue.poll(2, SECONDS);
        assertTrue(Arrays.asList(errors).contains("Долгота должна быть в диапазоне от -180 до 180 градусов"));
        assertTrue(Arrays.asList(errors).contains("Широта должна быть в диапазоне от -90 до 90 градусов"));
    }

    private class StringStompFrameHandler implements StompFrameHandler {
        @Override
        public Type getPayloadType(StompHeaders stompHeaders) {
            return String[].class;
        }

        @Override
        public void handleFrame(StompHeaders stompHeaders, Object o) {
            errorQueue.offer((String[]) o);
        }
    }

  /*  @Test
    public void wrongCoordinatesValuesFailedTest() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1.0/weather/by-coordinates")
                .param("lat", "-91")
                .param("lon", "181")
        )
                .andExpect(status().is4xxClientError())
                .andReturn().getResponse();
        String result = response.getContentAsString();
        assertTrue(result.contains("Долгота должна быть в диапазоне от -180 до 180 градусов"));
        assertTrue(result.contains("Широта должна быть в диапазоне от -90 до 90 градусов"));
    }*/

/*    @Test
    public void wrongCityIdValuesFailedTest() throws Exception {
        when(weatherServiceMock.getWeatherByCityId(anyInt())).thenThrow(new OpenWeatherApiException(""));
        mockMvc.perform(get("/api/v1.0/weather/by-city-id")
                .param("cityId", "abc")
        )
                .andExpect(status().is(400));

    }*/

   /* @Test
    public void wrongCityNameValuesFailedTest() throws Exception {
        when(weatherServiceMock.getWeatherByCityName(anyString())).thenThrow(new OpenWeatherApiNotFoundException(""));
        mockMvc.perform(get("/api/v1.0/weather/by-city-name")
                .param("cityName", "dsadasd")
        )
                .andExpect(status().is(404));
    }*/
}
