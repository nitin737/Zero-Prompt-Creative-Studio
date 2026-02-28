package com.zpcs.model.enums;

import com.zpcs.model.PromptDescribable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CameraComposition implements PromptDescribable {
    MACRO("Macro (Extreme Close-up)", "extreme macro close-up"),
    WIDE_ANGLE("Wide Angle", "wide angle"),
    DRONE_AERIAL("Drone/Aerial View", "drone aerial view"),
    EYE_LEVEL("Eye-Level", "eye-level"),
    ISOMETRIC("Isometric Angle", "isometric angle");

    private final String displayName;
    private final String promptFragment;
}
