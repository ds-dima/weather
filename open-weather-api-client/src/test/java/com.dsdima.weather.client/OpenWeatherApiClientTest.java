package com.dsdima.weather.client;

import com.dsdima.weather.client.config.ClientConfig;
import com.dsdima.weather.client.service.WeatherApiClient;
import com.dsdima.weather.model.WeatherInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * Created by dsshevchenko on 1/19/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ClientConfig.class, loader = AnnotationConfigContextLoader.class)
public class OpenWeatherApiClientTest {

    public static final String HTTP_API_OPENWEATHERMAP_BASE_URL = "http://api.openweathermap.org/data/2.5/weather";
    @Autowired
    private WeatherApiClient client;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    @Before
    public void setUp() throws Exception {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void shouldRetrieveWeatherByCityId() throws Exception {
        mockServer.expect(requestTo(HTTP_API_OPENWEATHERMAP_BASE_URL +
                "?appid=46f7323cb5c5febda15f2a2baaf4ed64&units=metric&id=596826"))
                  .andExpect(method(HttpMethod.GET))
                  .andRespond(withSuccess("{\n" +
                          "  \"main\":{\n" +
                          "    \"temp\":27.15,\n" +
                          "    \"pressure\":1001\n" +
                          "  },\n" +
                          "  \"wind\":{\n" +
                          "    \"speed\":3.1,\n" +
                          "    \"deg\":360\n" +
                          "  },\n" +
                          "\"name\":\"Murava\"" +
                          "}", MediaType.APPLICATION_JSON_UTF8));
        WeatherInfo response = client.getWeatherByCityId("596826");
        assertNotNull(response);
        assertEquals((Float) 27.15f, response.getTemperature());
        assertEquals((Integer) 1001, response.getPressure());
        assertEquals((Float) 3.1f, response.getWindSpeed());
        assertEquals((Integer) 360, response.getWindDirection());
        assertEquals("Murava", response.getCityName());
    }

    @Test
    public void shouldRetrieveWeatherByName() throws Exception {
        mockServer.expect(requestTo(HTTP_API_OPENWEATHERMAP_BASE_URL +
                "?appid=46f7323cb5c5febda15f2a2baaf4ed64&units=metric&q=Omsk"))
                  .andExpect(method(HttpMethod.GET))
                  .andRespond(withSuccess("{\n" +
                          "  \"main\":{\n" +
                          "    \"temp\":20.15,\n" +
                          "    \"pressure\":1001\n" +
                          "  },\n" +
                          "  \"wind\":{\n" +
                          "    \"speed\":5.45,\n" +
                          "    \"deg\":200\n" +
                          "  },\n" +
                          "\"name\":\"Omsk\"" +
                          "}", MediaType.APPLICATION_JSON_UTF8));
        WeatherInfo response = client.getWeatherByCityName("Omsk");
        assertNotNull(response);
        assertEquals((Float) 20.15f, response.getTemperature());
        assertEquals((Integer) 1001, response.getPressure());
        assertEquals((Float) 5.45f, response.getWindSpeed());
        assertEquals((Integer) 200, response.getWindDirection());
        assertEquals("Omsk", response.getCityName());
    }

    @Test
    public void shouldRetrieveWeatherByCoordinates() throws Exception {
        mockServer.expect(requestTo(HTTP_API_OPENWEATHERMAP_BASE_URL +
                "?appid=46f7323cb5c5febda15f2a2baaf4ed64&units=metric&lon=139&lat=36"))
                  .andExpect(method(HttpMethod.GET))
                  .andRespond(withSuccess("{\n" +
                          "  \"main\":{\n" +
                          "    \"temp\":4,\n" +
                          "    \"pressure\":1019\n" +
                          "  },\n" +
                          "  \"wind\":{\n" +
                          "    \"speed\":25.45,\n" +
                          "    \"deg\":100\n" +
                          "  },\n" +
                          "\"name\":\"Ogano\"" +
                          "}", MediaType.APPLICATION_JSON_UTF8));
        WeatherInfo response = client.getWeatherByCoordinates("36", "139");
        assertNotNull(response);
        assertEquals((Float) 4f, response.getTemperature());
        assertEquals((Integer) 1019, response.getPressure());
        assertEquals((Float) 25.45f, response.getWindSpeed());
        assertEquals((Integer) 100, response.getWindDirection());
        assertEquals("Ogano", response.getCityName());
    }
}
