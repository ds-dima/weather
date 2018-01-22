package com.dsdima.weather.client;

import com.dsdima.weather.client.config.ClientConfig;
import com.dsdima.weather.client.service.WeatherApiClient;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

/**
 * @author dsshevchenko
 * @since <pre>1/22/2018</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ClientConfig.class, loader = AnnotationConfigContextLoader.class)
public abstract class  OpenWeatherApiAbstractTest {

    protected static final String APP_ID = "appid=46f7323cb5c5febda15f2a2baaf4ed64";
    protected static final String HTTP_API_OPENWEATHERMAP_BASE_URL = "http://api.openweathermap.org/data/2.5/weather?";
    protected static final String UNITS_METRIC = "units=metric";
    protected static final String HTTP_API_OPENWEATHERMAP_FULL_URL =
            HTTP_API_OPENWEATHERMAP_BASE_URL + APP_ID + "&" + UNITS_METRIC;
    @Autowired
    protected WeatherApiClient client;

    @Autowired
    protected RestTemplate restTemplate;

    protected MockRestServiceServer mockServer;

    @Before
    public void setUp() throws Exception {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

}
