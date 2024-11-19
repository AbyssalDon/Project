package com.example.demo.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

//TODO: Use proper http status codes
@ControllerAdvice
public class GlobalExceptionHandler {
    Map<String, Object> errorDetails = new HashMap<>();

    @ExceptionHandler({UserAlreadyExistsException.class})
    public ResponseEntity<Object> handlePersonAlreadyExistsException(UserAlreadyExistsException exception){
        errorDetails.put("message", exception.getMessage());
        errorDetails.put("code", HttpStatus.FORBIDDEN.value());

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(errorDetails);
    }

    @ExceptionHandler({UserDoesNotExistException.class})
    public ResponseEntity<Object> handlePersonDoesNotExistException(UserDoesNotExistException exception){
        errorDetails.put("message", exception.getMessage());
        errorDetails.put("code", HttpStatus.NO_CONTENT.value());

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(errorDetails);
    }

    @ExceptionHandler({EmailTakenException.class})
    public ResponseEntity<Object> handleEmailTakenException(EmailTakenException exception) {
        errorDetails.put("message", exception.getMessage());
        errorDetails.put("code", HttpStatus.FORBIDDEN.value());

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(errorDetails);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintValidationException(ConstraintViolationException exception) {
        errorDetails.put("message", "Validation failed");
        errorDetails.put("code", HttpStatus.BAD_REQUEST.value());
        errorDetails.put("details", exception.getMessage());

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(errorDetails);
    }

    @ExceptionHandler(FilterBadTypeException.class)
    public ResponseEntity<Object> handleFilterBadTypeException(FilterBadTypeException exception) {
        errorDetails.put("message", "Bad Type!");
        errorDetails.put("code", HttpStatus.BAD_REQUEST.value());
        errorDetails.put("details", exception.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDetails);
    }

    @ExceptionHandler(AddressNotLinkedException.class)
    public ResponseEntity<Object> handleAddressNotLinkedException(AddressNotLinkedException exception) {
        errorDetails.put("message", "Address is not linked to any user!");
        errorDetails.put("code", HttpStatus.BAD_REQUEST.value());
        errorDetails.put("details", exception.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDetails);
    }

    @ExceptionHandler(UserAlreadyHasAddressException.class)
    public ResponseEntity<Object> handleUserAlreadyHasAddressException(UserAlreadyHasAddressException exception) {
        errorDetails.put("message", "User is already linked to an address!");
        errorDetails.put("code", HttpStatus.BAD_REQUEST.value());
        errorDetails.put("details", exception.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDetails);
    }
}
