package com.dsdima.weather.controller;

import com.dsdima.weather.client.service.exception.OpenWeatherApiException;
import com.dsdima.weather.client.service.exception.OpenWeatherApiNotFoundException;
import com.dsdima.weather.service.WeatherService;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author dsshevchenko
 * @since <pre>1/22/2018</pre>
 */
public class WeatherControllerExceptionTest extends AbstractControllerTest {

    @MockBean
    private WeatherService weatherServiceMock;

    @Test
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
    }

    @Test
    public void wrongCityIdValuesFailedTest() throws Exception {
        when(weatherServiceMock.getWeatherByCityId(anyInt())).thenThrow(new OpenWeatherApiException(""));
        mockMvc.perform(get("/api/v1.0/weather/by-city-id")
                .param("cityId", "abc")
        )
                .andExpect(status().is(400));

    }

    @Test
    public void wrongCityNameValuesFailedTest() throws Exception {
        when(weatherServiceMock.getWeatherByCityName(anyString())).thenThrow(new OpenWeatherApiNotFoundException(""));
        mockMvc.perform(get("/api/v1.0/weather/by-city-name")
                .param("cityName", "dsadasd")
        )
                .andExpect(status().is(404));
    }
}
