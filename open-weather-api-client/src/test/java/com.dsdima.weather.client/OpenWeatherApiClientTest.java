package com.dsdima.weather.client;

import com.dsdima.weather.client.config.ClientConfig;
import com.dsdima.weather.client.service.WeatherApiClient;
import com.dsdima.weather.model.WeatherInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.util.Assert;

/**
 * Created by dsshevchenko on 1/19/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ClientConfig.class, loader=AnnotationConfigContextLoader.class)
public class OpenWeatherApiClientTest {

    @Autowired
    private WeatherApiClient client;

    @Test
    public void shouldRetrieveWeatherByCityId() throws Exception {
        WeatherInfo response = client.getWeatherByCityId("596826");
        Assert.notNull(response);
    }
}
