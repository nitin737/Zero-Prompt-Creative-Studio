package com.zpcs.model.enums;

import com.zpcs.model.PromptDescribable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LightingSetup implements PromptDescribable {
    GOLDEN_HOUR("Natural/Golden Hour", "natural golden hour"),
    STUDIO_SOFTBOX("Studio Softbox", "studio softbox"),
    NEON_VOLUMETRIC("Neon Volumetric", "neon volumetric"),
    HIGH_CONTRAST("High Contrast/Moody", "high contrast moody"),
    HARSH_SUNLIGHT("Harsh Sunlight", "harsh direct sunlight");

    private final String displayName;
    private final String promptFragment;
}
