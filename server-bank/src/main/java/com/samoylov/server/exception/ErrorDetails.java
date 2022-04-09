package com.samoylov.server.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDetails {
    private Date timestamp;
    private String status;
    private HttpStatus error;
    private String message;
    private String details;
}
