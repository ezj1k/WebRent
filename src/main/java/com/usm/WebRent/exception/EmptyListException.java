package com.usm.WebRent.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class EmptyListException extends RuntimeException {
    public EmptyListException(String resourceName) {
        super("Nu am găsit niciun element în lista de " + resourceName);
    }
}
