package com.zpcs.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class QuotaExceededException extends ZpcsException {

    private final int retryAfterSeconds;

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.TOO_MANY_REQUESTS;
    }

    @Override
    public String getErrorCode() {
        return "QUOTA_EXCEEDED";
    }

    public QuotaExceededException(int retryAfterSeconds) {
        super("API rate limit exceeded. Try again in " + retryAfterSeconds + " seconds.");
        this.retryAfterSeconds = retryAfterSeconds;
    }
}
