package com.intive.currencyExchange.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<ApiError> handleException(NoSuchElementException e) {
        return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, e.getMessage()));
    }

    @ExceptionHandler(value = HttpClientErrorException.class)
    public ResponseEntity<ApiError> handleException(HttpClientErrorException e) {
        return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, "No data found!"));
    }

    private ResponseEntity<ApiError> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.httpStatus());
    }
}
