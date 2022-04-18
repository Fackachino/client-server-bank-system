package com.samoylov.client.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class LoginClientException extends RuntimeException{
    public LoginClientException(String message){
        super(message);
    }
}
