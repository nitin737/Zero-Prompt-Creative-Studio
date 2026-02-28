package com.zpcs.model;

import com.zpcs.dto.request.GenerateImageRequest;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GenerationContext {
    String id;
    String prompt;
    GenerateImageRequest originalRequest;
    GeminiRequestConfig aiConfig;
    byte[] sourceImageBytes;
}
