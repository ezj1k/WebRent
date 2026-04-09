package com.usm.WebRent.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class LocationDeletionException extends RuntimeException {
    public LocationDeletionException(Long id) {
        super("Nu s-a putut șterge locația " + id + ". Verificați dacă este legată de alte închirieri.");
    }
}
