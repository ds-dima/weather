package com.dsdima.weather.config;

import com.dsdima.weather.converter.WeatherConverter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.util.List;

/**
 * Mvc configuration
 *
 * @author dsshevchenko
 * @since <pre>1/22/2018</pre>
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    /**
     * Message converter for java <-> json transformation
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper());
        converters.add(converter);
    }

    /**
     * Define {@link WeatherConverter} converter bean
     * @param registry registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new WeatherConverter());
    }

    /**
     * Object mapper.
     * @return Object mapper.
     */
    @Bean
    public ObjectMapper objectMapper() {
        return objectMapperBuilder().build();
    }

    /**
     * Object mapper builder with additional modules registered in.
     * @return Object mapper.
     */
    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        return new Jackson2ObjectMapperBuilder()
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .featuresToEnable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY)
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .failOnUnknownProperties(false);
    }
}
