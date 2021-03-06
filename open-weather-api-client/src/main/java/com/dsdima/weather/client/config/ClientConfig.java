package com.dsdima.weather.client.config;

import com.dsdima.weather.client.service.exception.OpenWeatherApiException;
import com.dsdima.weather.client.service.exception.OpenWeatherApiNotFoundException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.Charset;

import static java.lang.String.format;

/**
 * Client configuration
 * @author dsshevchenko
 * @since <pre>1/22/2018</pre>
 */
@Configuration
@ComponentScan(basePackages = "com.dsdima.weather.client")
@PropertySource("classpath:application.properties")
public class ClientConfig  {

    /**
     * Rest template bean with exception handler
     * @return bean
     */
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler(){
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                if (getHttpStatusCode(response).is5xxServerError()) {
                    throw new OpenWeatherApiException(format("Ошибка работы провайдера погоды. (%s)", response.getStatusText()));
                }
                if (getHttpStatusCode(response).equals(HttpStatus.UNAUTHORIZED)) {
                    throw new OpenWeatherApiException(format("Ошибка авторизации, уточните корректность 'appid' (%s)",
                            response.getStatusText()));
                }
                if (getHttpStatusCode(response).equals(HttpStatus.BAD_REQUEST)) {
                    throw new OpenWeatherApiException(format("Неправильные параметры запроса. (%s)", response.getStatusText()));
                }
                if (getHttpStatusCode(response).equals(HttpStatus.NOT_FOUND)) {
                    throw new OpenWeatherApiNotFoundException("По данному запросу ничего не найдено");
                }
            }
        });
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }
}
