package com.zpcs.dto.response;

import lombok.Builder;
import lombok.Value;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

@Value
@Builder
public class ErrorResponse {
    String error;
    String message;
    LocalDateTime timestamp;
    @Nullable
    Integer retryAfterSeconds;
}
