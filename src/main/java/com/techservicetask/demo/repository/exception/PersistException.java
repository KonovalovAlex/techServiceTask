package com.techservicetask.demo.repository.exception;

public class PersistException extends RuntimeException {


    public PersistException(String message) {
        super(message);
    }

    public PersistException(String message, Throwable cause) {
        super(message, cause);
    }
}
