package com.zpcs.dto.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GeneratedImageResponse {
    String id;
    String imageUrl;
    String prompt;
    long generationTimeMs;
    ImageMetadata metadata;
}
