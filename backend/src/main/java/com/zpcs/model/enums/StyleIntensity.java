package com.zpcs.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StyleIntensity {
    SUBTLE("Subtle", 0.3),
    BALANCED("Balanced", 0.6),
    AGGRESSIVE("Aggressive", 0.9);

    private final String displayName;
    private final double weight;
}
