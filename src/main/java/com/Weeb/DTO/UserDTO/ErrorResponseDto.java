package com.Weeb.DTO.UserDTO;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(content = JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class ErrorResponseDto implements Serializable {
    private HttpStatus status;
    private String error;
    private String message;
    private String timestamp;

    public ErrorResponseDto(HttpStatus status, String error, String messageString) {
        this.status = status;
        this.error = error;
        this.message = messageString;
        this.timestamp = LocalDateTime.now().toString();
    }
}