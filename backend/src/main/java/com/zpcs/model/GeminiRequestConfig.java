package com.zpcs.model;

import com.zpcs.model.enums.ThinkingLevel;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GeminiRequestConfig {
    String model;
    ThinkingLevel thinkingLevel;
    String aspectRatio;
    int imageWidth;
    int imageHeight;
}
