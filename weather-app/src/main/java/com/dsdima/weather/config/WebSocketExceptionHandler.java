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

import com.dsdima.weather.controller.WeatherWebSocketController;
import com.dsdima.weather.exception.WeatherException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dsshevchenko
 * @since <pre>1/15/2018</pre>
 */
@ControllerAdvice
public class WebSocketExceptionHandler {

    /** Logger */
    private static final Logger LOG = LoggerFactory.getLogger(WeatherWebSocketController.class);

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    /**
     * Handle weather exception
     * @param ex exception
     */
    @MessageExceptionHandler(WeatherException.class)
    public void handleInternalException(WeatherException ex) {
        LOG.error("OpenWeatherApi system error", ex);
    }

    /**
     * Handle Interrupted Exception
     * @param ex exception
     */
    @MessageExceptionHandler(InterruptedException.class)
    public void handleInterruptedException(InterruptedException ex) {
        LOG.error("System error", ex);
    }

    /**
     * Bad request params
     * @param ex exception
     */
    @MessageExceptionHandler(MethodArgumentNotValidException.class)
    public void handleBadRequestException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        LOG.error("Bad request params: {}", errors.stream().collect(Collectors.joining(",")));
//        messagingTemplate.convertAndSend();
    }

}
