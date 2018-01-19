package com.dsdima.weather.client.config;

import com.dsdima.weather.client.converter.OpenWeatherResponseConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by dsshevchenko on 1/19/18.
 */
@Configuration
@ComponentScan(basePackages = "com.dsdima.weather.client")
@PropertySource("classpath:application.properties")
public class ClientConfig extends WebMvcConfigurerAdapter {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new OpenWeatherResponseConverter());
    }
}
