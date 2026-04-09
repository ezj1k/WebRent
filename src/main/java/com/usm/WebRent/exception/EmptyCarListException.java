package com.usm.WebRent.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class EmptyCarListException extends RuntimeException {
    public EmptyCarListException() {
        super("Nu există nicio mașină în listă.");
    }
}