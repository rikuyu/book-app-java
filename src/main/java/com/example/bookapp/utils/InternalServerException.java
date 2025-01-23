package com.example.bookapp.utils;

public class InternalServerException extends RuntimeException {

    public InternalServerException(String message, Throwable cause) {
        super(message, cause);
    }
}
