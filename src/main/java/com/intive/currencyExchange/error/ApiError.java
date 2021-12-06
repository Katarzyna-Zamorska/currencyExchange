package com.intive.currencyExchange.error;

import org.springframework.http.HttpStatus;

public record ApiError(HttpStatus httpStatus, String message) {
}
