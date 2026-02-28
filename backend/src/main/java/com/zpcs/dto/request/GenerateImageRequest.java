package com.zpcs.dto.request;

import com.zpcs.model.enums.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenerateImageRequest {

    @NotBlank(message = "Subject is required")
    @Size(max = 500, message = "Subject must be under 500 characters")
    private String subject;

    @NotNull(message = "Operation mode is required")
    private OperationMode operationMode;

    private AestheticStyle aestheticStyle;
    private LightingSetup lighting;
    private CameraComposition cameraComposition;
    private ColorPalette colorPalette;
    private LensEffect lensEffect;

    @NotNull(message = "Aspect ratio is required")
    private AspectRatio aspectRatio;

    @NotNull(message = "Resolution is required")
    private ResolutionQuality resolution;

    @NotNull(message = "Thinking level is required")
    private ThinkingLevel thinkingLevel;

    private StyleIntensity styleIntensity;
    private String sourceImageBase64;
}
