package com.usm.WebRent.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserUpdateException extends RuntimeException {
    public UserUpdateException(String detail) {
        super("Nu s-a putut actualiza profilul: " + detail);
    }
}