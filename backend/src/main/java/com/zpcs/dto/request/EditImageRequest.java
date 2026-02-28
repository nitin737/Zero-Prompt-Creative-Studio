package com.zpcs.dto.request;

import com.zpcs.model.enums.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditImageRequest {

    @NotBlank(message = "Subject is required")
    private String subject;

    @NotBlank(message = "Source image is required")
    private String sourceImageBase64;

    @NotNull(message = "Operation mode is required")
    private OperationMode operationMode;

    private AestheticStyle aestheticStyle;
    private LightingSetup lighting;
    private StyleIntensity styleIntensity;

    private AspectRatio aspectRatio;
    private ResolutionQuality resolution;
    private ThinkingLevel thinkingLevel;
}
