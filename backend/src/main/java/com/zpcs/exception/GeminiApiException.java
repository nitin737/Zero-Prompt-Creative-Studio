package com.zpcs.exception;

import org.springframework.http.HttpStatus;

public class GeminiApiException extends ZpcsException {

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_GATEWAY;
    }

    @Override
    public String getErrorCode() {
        return "UPSTREAM_ERROR";
    }

    public GeminiApiException(String message) {
        super(message);
    }

    public GeminiApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
