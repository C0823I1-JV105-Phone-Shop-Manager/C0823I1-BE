package com.example.c0823l1_be.controller;

import com.example.c0823l1_be.entity.ValidateErrorResponse;
import com.example.c0823l1_be.entity.ErrorResponse;
import org.springframework.http.*;
import org.springframework.validation.FieldError;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    //override method of ResponseEntityExceptionHandler class
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request)
    {
        //creating exception response structure
        ErrorResponse exceptionResponse= new ErrorResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        //returning exception structure and specific status
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String,String> details = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            details.put(error.getField(),error.getDefaultMessage());
        }

        ValidateErrorResponse error = new ValidateErrorResponse("Validation Failed", details);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
}