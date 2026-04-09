package com.usm.WebRent.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CarSaveException extends RuntimeException {
    public CarSaveException(String message) {
        super("Eroare la salvarea mașinii: " + message);
    }
}