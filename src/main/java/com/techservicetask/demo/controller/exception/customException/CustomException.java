package com.techservicetask.demo.controller.exception.customException;

public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }

    public CustomException() {
        super();
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomException(Throwable cause) {
        super(cause);
    }

    protected CustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
