package com.zpcs.model.enums;

import com.zpcs.model.PromptDescribable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LensEffect implements PromptDescribable {
    DEEP_FOCUS("Deep Focus", "deep focus sharp"),
    SHALLOW_DOF("Shallow Depth of Field", "shallow depth of field bokeh"),
    MOTION_BLUR("Motion Blur", "motion blur"),
    FISHEYE("Fisheye", "fisheye distortion");

    private final String displayName;
    private final String promptFragment;
}
