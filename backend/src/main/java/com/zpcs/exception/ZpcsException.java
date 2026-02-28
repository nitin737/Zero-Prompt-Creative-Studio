package com.zpcs.exception;

import org.springframework.http.HttpStatus;

/**
 * Abstract base exception for the application.
 * All domain exceptions extend this — enabling polymorphic handling (LSP).
 */
public abstract class ZpcsException extends RuntimeException {

    public abstract HttpStatus getHttpStatus();

    public abstract String getErrorCode();

    protected ZpcsException(String message) {
        super(message);
    }

    protected ZpcsException(String message, Throwable cause) {
        super(message, cause);
    }
}
