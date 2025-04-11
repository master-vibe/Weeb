package com.Weeb.Exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class UserException extends RuntimeException{
    private HttpStatus status;

    public UserException() {
        this.status = HttpStatus.BAD_REQUEST;
    }

    public UserException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
