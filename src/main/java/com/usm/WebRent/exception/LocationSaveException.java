package com.usm.WebRent.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LocationSaveException extends RuntimeException {
    public LocationSaveException(String message) {
        super("Eroare la salvarea locației: " + message);
    }
}
