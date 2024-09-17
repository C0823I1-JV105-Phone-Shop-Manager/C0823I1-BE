package com.example.c0823l1_be.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
    private HttpStatus status;
    private LocalDateTime timestamp;
    private String message;
    private List<String> errors;
    public ErrorResponse(HttpStatus status,LocalDateTime timestamp, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }
}
