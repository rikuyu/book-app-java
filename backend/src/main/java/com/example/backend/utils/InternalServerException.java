package com.example.backend.utils;

public class InternalServerException extends RuntimeException {

    public InternalServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalServerException(Throwable cause) {
        super(cause);
    }

    public InternalServerException(String message) {
        super(message);
    }
}
