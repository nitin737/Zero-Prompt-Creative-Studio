package com.zpcs.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResolutionQuality {
    DRAFT("Draft", 512, 512),
    STANDARD("Standard", 1024, 1024),
    PRODUCTION("Production", 2048, 2048);

    private final String displayName;
    private final int width;
    private final int height;
}
