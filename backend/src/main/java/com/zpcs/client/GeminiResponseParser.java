package com.zpcs.client;

import lombok.extern.slf4j.Slf4j;

import java.util.Base64;
import java.util.List;
import java.util.Map;

/**
 * Utility class to parse the raw Gemini API JSON response
 * and extract the generated image bytes.
 */
@Slf4j
public final class GeminiResponseParser {

    private GeminiResponseParser() {
    }

    @SuppressWarnings("unchecked")
    public static byte[] extractImageBytes(Map<String, Object> responseMap) {
        try {
            List<Map<String, Object>> candidates = (List<Map<String, Object>>) responseMap.get("candidates");
            if (candidates == null || candidates.isEmpty()) {
                throw new IllegalStateException("No candidates in Gemini response");
            }

            Map<String, Object> content = (Map<String, Object>) candidates.get(0).get("content");
            List<Map<String, Object>> parts = (List<Map<String, Object>>) content.get("parts");

            for (Map<String, Object> part : parts) {
                if (part.containsKey("inlineData")) {
                    Map<String, Object> inlineData = (Map<String, Object>) part.get("inlineData");
                    String base64Data = (String) inlineData.get("data");
                    return Base64.getDecoder().decode(base64Data);
                }
            }

            throw new IllegalStateException("No image data found in Gemini response");
        } catch (ClassCastException | NullPointerException e) {
            log.error("Failed to parse Gemini response structure", e);
            throw new IllegalStateException("Invalid Gemini response format", e);
        }
    }
}
