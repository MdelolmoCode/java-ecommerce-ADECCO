package com.example.exception;

public class EntitySavingException extends RuntimeException {
    public EntitySavingException(String message) {
        super(message);
    }
}
