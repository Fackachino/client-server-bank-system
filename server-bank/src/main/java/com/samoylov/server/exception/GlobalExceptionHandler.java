package com.samoylov.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CardNotFoundException.class)
    public ResponseEntity<?> handleCardNotFoundException(CardNotFoundException exception, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                "404",
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WrongPinException.class)
    public ResponseEntity<?> handleWrongPinException(WrongPinException exception, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                "403",
                HttpStatus.FORBIDDEN,
                exception.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<?> handleWrongPinException(AccountNotFoundException exception, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                "404",
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandling(Exception exception, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                "500",
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
