package com.example.poke_api.exception;

public class ValidationException extends RuntimeException {
    public ValidationException(String message){
        super(message);
    }
}
