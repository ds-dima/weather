package com.dsdima.weather.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Configuration
 *
 * @author dsshevchenko
 * @since <pre>1/23/2018</pre>
 */
@Configuration
public class Config {

    /**
     * Task executor service for singleton requests from webapp
     * @return executor service bean
     */
    @Bean
    public ExecutorService weatherTaskExecutorService() {
        return Executors.newFixedThreadPool(1);
    }
}
