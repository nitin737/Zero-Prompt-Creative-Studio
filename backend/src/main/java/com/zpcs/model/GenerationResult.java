package com.zpcs.model;

import lombok.Builder;
import lombok.Value;

import java.util.Map;

@Value
@Builder
public class GenerationResult {
    byte[] imageData;
    String mimeType;
    Map<String, Object> modelMetadata;
}
