package com.usm.WebRent.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidRentalDatesException extends RuntimeException {
    public InvalidRentalDatesException(String message) {
        super(message);
    }
}