package com.samoylov.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class WrongPinException extends RuntimeException{
    public WrongPinException(String message) {
        super(message);
    }
}
