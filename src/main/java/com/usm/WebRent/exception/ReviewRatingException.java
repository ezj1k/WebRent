package com.usm.WebRent.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ReviewRatingException extends RuntimeException {
    public ReviewRatingException() {
        super("Nota trebuie să fie între 1 și 5!");
    }
}