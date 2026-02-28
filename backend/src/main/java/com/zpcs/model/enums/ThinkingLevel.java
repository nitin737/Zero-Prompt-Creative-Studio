package com.zpcs.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ThinkingLevel {
    FAST("Fast", "minimal"),
    CREATIVE("Creative", "high");

    private final String displayName;
    private final String apiValue;
}
