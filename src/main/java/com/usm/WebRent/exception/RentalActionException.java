package com.usm.WebRent.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class RentalActionException extends RuntimeException {
    public RentalActionException(String action) {
        super("Această acțiune (" + action + ") nu este permisă pentru rezervarea respectivă!");
    }
}