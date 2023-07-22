package com.sinulingga.train.exception;

public class CodeAlreadyExistsException extends Exception {
    public CodeAlreadyExistsException(String message) {
        super(message);
    }

    public CodeAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public CodeAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
