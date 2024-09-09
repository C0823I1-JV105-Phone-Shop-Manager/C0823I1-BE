package com.example.c0823l1_be.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class ValidateErrorResponse {

    public ValidateErrorResponse(String message, Map<String,String> details) {
        super();
        this.message = message;
        this.details = details;
    }

    //General error message about nature of error
    private String message;

    //Specific errors in API request processing
    private Map<String,String> details;
}