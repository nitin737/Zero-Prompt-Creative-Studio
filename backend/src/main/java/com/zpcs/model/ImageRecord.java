package com.zpcs.model;

import com.zpcs.model.enums.OperationMode;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class ImageRecord {
    String id;
    String filePath;
    String prompt;
    OperationMode operationMode;
    long generationTimeMs;
    LocalDateTime createdAt;
}
