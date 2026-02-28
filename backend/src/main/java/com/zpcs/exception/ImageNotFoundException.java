package com.zpcs.exception;

import org.springframework.http.HttpStatus;

public class ImageNotFoundException extends ZpcsException {

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public String getErrorCode() {
        return "NOT_FOUND";
    }

    public ImageNotFoundException(String id) {
        super("Image not found: " + id);
    }
}
