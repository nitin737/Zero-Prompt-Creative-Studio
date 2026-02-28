package com.zpcs.service;

import com.zpcs.model.enums.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Provides all dropdown options to the frontend.
 * Reads enum values dynamically — no hardcoded lists.
 */
@Service
public class OptionsProvider {

    public Map<String, List<Map<String, String>>> getAllOptions() {
        Map<String, List<Map<String, String>>> options = new LinkedHashMap<>();
        options.put("operationModes", mapEnum(OperationMode.values()));
        options.put("aestheticStyles", mapEnum(AestheticStyle.values()));
        options.put("lightingSetups", mapEnum(LightingSetup.values()));
        options.put("cameraCompositions", mapEnum(CameraComposition.values()));
        options.put("colorPalettes", mapEnum(ColorPalette.values()));
        options.put("lensEffects", mapEnum(LensEffect.values()));
        options.put("aspectRatios", mapAspectRatio(AspectRatio.values()));
        options.put("resolutions", mapEnum(ResolutionQuality.values()));
        options.put("styleIntensities", mapEnum(StyleIntensity.values()));
        options.put("thinkingLevels", mapEnum(ThinkingLevel.values()));
        return options;
    }

    private <E extends Enum<E>> List<Map<String, String>> mapEnum(E[] values) {
        return Arrays.stream(values)
                .map(v -> {
                    Map<String, String> item = new LinkedHashMap<>();
                    item.put("value", v.name());
                    try {
                        item.put("label", (String) v.getClass().getMethod("getDisplayName").invoke(v));
                    } catch (Exception e) {
                        item.put("label", v.name());
                    }
                    return item;
                })
                .collect(Collectors.toList());
    }

    private List<Map<String, String>> mapAspectRatio(AspectRatio[] values) {
        return Arrays.stream(values)
                .map(v -> {
                    Map<String, String> item = new LinkedHashMap<>();
                    item.put("value", v.name());
                    item.put("label", v.getDisplayName());
                    item.put("apiValue", v.getApiValue());
                    return item;
                })
                .collect(Collectors.toList());
    }
}
