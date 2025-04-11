package com.Weeb.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.Weeb.DTO.UserDTO.ErrorResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorResponseDto> handleUserException(UserException ex) {
	    return new ResponseEntity<>(new ErrorResponseDto(ex.getStatus(), "User Error",ex.getMessage()), ex.getStatus());
    }
}
