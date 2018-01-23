/*
 * Copyright 2018 Russian Post
 *
 * This source code is Russian Post Confidential Proprietary.
 * This software is protected by copyright. All rights and titles are reserved.
 * You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
 * Otherwise this violation would be treated by law and would be subject to legal prosecution.
 * Legal use of the software provides receipt of a license from the right holder only.
 */
package com.dsdima.weather.config;

import com.dsdima.weather.client.service.exception.OpenWeatherApiException;
import com.dsdima.weather.client.service.exception.OpenWeatherApiNotFoundException;
import com.dsdima.weather.controller.WeatherWebSocketController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * @author dsshevchenko
 * @since <pre>1/15/2018</pre>
 */
@ControllerAdvice
public class RestExceptionHandler {

    /** Logger */
    private static final Logger LOG = LoggerFactory.getLogger(WeatherWebSocketController.class);

    /**
     * Handle weather api client errors
     * @param ex exception
     */
    @MessageExceptionHandler(OpenWeatherApiException.class)
    public void handleInternalException(OpenWeatherApiException ex) {
        LOG.error("OpenWeatherApi system error", ex);
    }

    /**
     * Handle weather api not found errors
     * @param ex exception
     */
    @MessageExceptionHandler(OpenWeatherApiNotFoundException.class)
    public void handleNotFoundException(OpenWeatherApiNotFoundException ex) {
        LOG.error("Request search not found any data", ex);
    }

    /**
     * Handle Interrupted Exception
     * @param ex exception
     */
    @MessageExceptionHandler(InterruptedException.class)
    public void handleNotFoundException(InterruptedException ex) {
        LOG.error("System error", ex);
    }

    /**
     * Bad request params
     * @param ex exception
     */
    @MessageExceptionHandler(MethodArgumentNotValidException.class)
    public void handleBadRequestException(MethodArgumentNotValidException ex) {
        String error = ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(","));
        LOG.error("Bad request params: {}", error);
    }

}
