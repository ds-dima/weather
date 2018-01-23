package com.dsdima.weather.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author dsshevchenko
 * @since <pre>1/23/2018</pre>
 */
@Configuration
public class Config {

    @Bean
    public ExecutorService weatherTaskExecutorService() {
        return Executors.newFixedThreadPool(1);
    }
}
