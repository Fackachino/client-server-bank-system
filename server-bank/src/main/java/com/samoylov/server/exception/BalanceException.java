package com.samoylov.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BalanceException extends RuntimeException{
    public BalanceException(String message) {
        super(message);
    }
}
