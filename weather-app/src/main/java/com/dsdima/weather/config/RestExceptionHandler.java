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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author dsshevchenko
 * @since <pre>1/15/2018</pre>
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle weather api client errors
     * @param ex exception
     * @return response entity
     */
    @ExceptionHandler(OpenWeatherApiException.class)
    protected ResponseEntity<String> handleInternalException(OpenWeatherApiException ex) {
        //todo log
        return new ResponseEntity<>("Ошибка работы системы, обратитесь к разработчикам", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handle weather api not found errors
     * @param ex exception
     * @return response entity
     */
    @ExceptionHandler(OpenWeatherApiNotFoundException.class)
    protected ResponseEntity<String> handleNotFoundException(OpenWeatherApiNotFoundException ex) {
        //todo log
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Bad request exception handler
     * @param ex exception
     * @return response entity
     */
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<List<String>> handleBadRequestException(ConstraintViolationException ex) {
        //todo log
        List<String> errors = ex.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
