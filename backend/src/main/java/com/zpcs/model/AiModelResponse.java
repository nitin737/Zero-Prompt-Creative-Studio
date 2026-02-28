package com.zpcs.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AiModelResponse {
    byte[] imageData;
    String mimeType;
    long processingTimeMs;
}
