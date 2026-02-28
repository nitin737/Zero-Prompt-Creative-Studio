package com.zpcs.model;

import com.zpcs.model.enums.StyleIntensity;
import com.zpcs.model.enums.ThinkingLevel;
import lombok.Builder;
import lombok.Value;
import org.springframework.lang.Nullable;

@Value
@Builder
public class AiModelRequest {
    String prompt;
    String model;
    ThinkingLevel thinkingLevel;
    String aspectRatio;
    int width;
    int height;
    @Nullable
    byte[] sourceImage;
    @Nullable
    StyleIntensity intensity;
}
