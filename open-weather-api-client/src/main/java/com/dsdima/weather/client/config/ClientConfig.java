package com.dsdima.weather.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

/**
 * Created by dsshevchenko on 1/19/18.
 */
@Configuration
@ComponentScan(basePackages = "com.dsdima.weather.client")
@PropertySource("classpath:application.properties")
public class ClientConfig  {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
