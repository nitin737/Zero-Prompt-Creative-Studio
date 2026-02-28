package com.zpcs.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OperationMode {
    TEXT_TO_IMAGE("Generate New"),
    EDIT_EXISTING("Edit Existing"),
    STYLE_TRANSFER("Style Transfer"),
    MULTI_IMAGE("Multi-Image Composition");

    private final String displayName;
}
