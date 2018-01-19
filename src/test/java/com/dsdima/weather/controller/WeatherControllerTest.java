package com.dsdima.weather.controller;

import com.dsdima.weather.controller.json.WeatherInfoJson;
import com.dsdima.weather.converter.WindDirection;
import com.dsdima.weather.model.WeatherInfo;
import com.dsdima.weather.service.WeatherService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by dsshevchenko on 1/19/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherService weatherServiceMock;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldRetrieveWeatherByCityName() throws Exception {
        when(weatherServiceMock.getWeatherByCityName(anyString()))
                .thenReturn(new WeatherInfo()
                        .setTemperature(20f)
                        .setPressure(1021)
                        .setWindSpeed(20.5f)
                        .setWindDirection(350)
                );

        String weatherJsonText = mockMvc.perform(get("/api/v1.0/weather/by-city-name")
                                            .param("cityName", anyString()))
                .andExpect(status().is2xxSuccessful())
                .andReturn().getResponse().getContentAsString();

        assertNotNull(weatherJsonText);
        WeatherInfoJson json = objectMapper.readValue(weatherJsonText, WeatherInfoJson.class);
        assertEquals(new Integer(20), json.getTemperature());
        assertEquals(new Integer(765), json.getPressure());
        assertEquals(new Integer(20), json.getWindSpeed());
        assertEquals(WindDirection.NORTH.getDescription(), json.getWindDirection());
    }

    @Test
    public void shouldRetrieveWeatherByCityId() throws Exception {
        when(weatherServiceMock.getWeatherByCityId(anyString()))
                .thenReturn(new WeatherInfo()
                        .setTemperature(-5.9f)
                        .setPressure(1080)
                        .setWindDirection(200)
                );

        String weatherJsonText = mockMvc.perform(get("/api/v1.0/weather/by-city-id")
                                            .param("cityId", anyString()))
                .andExpect(status().is2xxSuccessful())
                .andReturn().getResponse().getContentAsString();
        assertNotNull(weatherJsonText);
        WeatherInfoJson json = objectMapper.readValue(weatherJsonText, WeatherInfoJson.class);
        assertEquals(new Integer(-5), json.getTemperature());
        assertEquals(new Integer(810), json.getPressure());
        assertNull(json.getWindSpeed());
        assertEquals(WindDirection.SOUTH.getDescription(), json.getWindDirection());
    }

    @Test
    public void shouldRetrieveWeatherByCoordinates() throws Exception {
        when(weatherServiceMock.getWeatherByCoordinates(anyString(), anyString()))
                .thenReturn(new WeatherInfo()
                        .setTemperature(-35f)
                        .setWindDirection(300)
                );

        String weatherJsonText = mockMvc.perform(get("/api/v1.0/weather/by-coordinates")
                                            .param("lat", anyString())
                                            .param("lon", anyString())
        )
                .andExpect(status().is2xxSuccessful())
                .andReturn().getResponse().getContentAsString();
        assertNotNull(weatherJsonText);
        WeatherInfoJson json = objectMapper.readValue(weatherJsonText, WeatherInfoJson.class);
        assertEquals(new Integer(-35), json.getTemperature());
        assertNull(json.getPressure());
        assertNull(json.getWindSpeed());
        assertEquals(WindDirection.NORTH_WEST.getDescription(), json.getWindDirection());
    }
}
