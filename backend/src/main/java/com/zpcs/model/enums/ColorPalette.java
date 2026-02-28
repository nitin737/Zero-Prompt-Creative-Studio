package com.zpcs.model.enums;

import com.zpcs.model.PromptDescribable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ColorPalette implements PromptDescribable {
    VIBRANT("Vibrant/Saturated", "vibrant saturated"),
    MUTED("Muted/Pastel", "muted pastel"),
    MONOCHROMATIC("Monochromatic", "monochromatic"),
    SEPIA("Sepia", "sepia-toned"),
    HIGH_CONTRAST_BW("High Contrast Black & White", "high contrast black and white");

    private final String displayName;
    private final String promptFragment;
}
