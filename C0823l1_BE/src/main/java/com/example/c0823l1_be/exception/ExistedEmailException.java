package com.example.c0823l1_be.exception;


public class ExistedEmailException extends RuntimeException{
    public ExistedEmailException(String errorMessage) {
        super(errorMessage);
    }
}
