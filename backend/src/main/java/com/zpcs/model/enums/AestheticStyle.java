package com.zpcs.model.enums;

import com.zpcs.model.PromptDescribable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AestheticStyle implements PromptDescribable {
    PHOTOREALISTIC("Photorealistic", "photorealistic"),
    ISOMETRIC_3D("Isometric 3D", "isometric 3D rendered"),
    FLAT_VECTOR("Flat Vector", "flat vector illustration"),
    CINEMATIC("Cinematic", "cinematic film still"),
    CYBERPUNK("Cyberpunk", "cyberpunk neon-drenched"),
    WATERCOLOR("Watercolor", "traditional watercolor painting"),
    SKETCH("Sketch", "detailed charcoal sketch"),
    POP_ART("Pop Art", "bold pop art");

    private final String displayName;
    private final String promptFragment;
}
