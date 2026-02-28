package com.zpcs.dto.response;

import com.zpcs.model.enums.ThinkingLevel;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class ImageMetadata {
    String model;
    ThinkingLevel thinkingLevel;
    String aspectRatio;
    String resolution;
    LocalDateTime createdAt;
}
