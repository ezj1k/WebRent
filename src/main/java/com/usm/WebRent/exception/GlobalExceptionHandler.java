package com.usm.WebRent.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 1. GRUPA: RESURSA NU A FOST GĂSITĂ (Status 404)
    @ExceptionHandler({
            CarNotFoundException.class,
            UserNotFoundException.class,
            RentalNotFoundException.class,
            LocationNotFoundException.class,
            ReviewNotFoundException.class,
            EmptyCarListException.class // Am pus-o aici ca să fie vizibil mesajul în browser
    })
    public ResponseEntity<String> handleNotFound(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // 2. GRUPA: DATE INVALIDE SAU ERORI DE SALVARE (Status 400)
    @ExceptionHandler({
            InvalidRentalDatesException.class,
            CarSaveException.class,
            UserUpdateException.class,
            ReviewRatingException.class,
            ReviewNotAllowedException.class
    })
    public ResponseEntity<String> handleBadRequest(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // 3. GRUPA: CONFLICTE SAU ACȚIUNI INTERZISE (Status 409 sau 403)
    @ExceptionHandler({
            EmailAlreadyExistsException.class,
            CarAlreadyRentedException.class
    })
    public ResponseEntity<String> handleConflict(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RentalActionException.class)
    public ResponseEntity<String> handleForbiddenAction(RentalActionException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    // 4. "PLASA DE SIGURANȚĂ": Prinde orice altă eroare neprevăzută
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return new ResponseEntity<>("A intervenit o eroare neprevăzută: " + ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


