package com.usm.WebRent.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CarAlreadyRentedException extends RuntimeException {
    public CarAlreadyRentedException(Long carId) {
        super("Mașina cu id: " + carId + " este deja rezervată pentru perioada selectată!");
    }
}