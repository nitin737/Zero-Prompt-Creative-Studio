package com.zpcs.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AspectRatio {
    RATIO_1_1("1:1 (Square)", "1:1"),
    RATIO_16_9("16:9 (Landscape)", "16:9"),
    RATIO_9_16("9:16 (Vertical)", "9:16"),
    RATIO_4_3("4:3 (Standard)", "4:3"),
    RATIO_21_9("21:9 (Cinematic)", "21:9");

    private final String displayName;
    private final String apiValue;
}
