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
        mockServer.expect(requestTo("http://api.openweathermap.org/data/2.5/weather?id=596826&appid=46f7323cb5c5febda15f2a2baaf4ed64&units=metric"))
                  .andExpect(method(HttpMethod.GET))
                  .andRespond(withSuccess("{\n" +
                          "  \"main\":{\n" +
                          "    \"temp\":271.15,\n" +
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
        assertEquals((Float) 271.15f, response.getTemperature());
        assertEquals((Integer) 1001, response.getPressure());
        assertEquals((Float) 3.1f, response.getWindSpeed());
        assertEquals((Integer) 360, response.getWindDirection());
    }



}
