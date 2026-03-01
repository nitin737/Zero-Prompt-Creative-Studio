package com.zpcs.client;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Part;
import com.zpcs.config.GeminiProperties;
import com.zpcs.exception.GeminiApiException;
import com.zpcs.model.AiModelRequest;
import com.zpcs.model.AiModelResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Adapter Pattern: adapts the external Gemini API into our internal
 * AiModelResponse.
 * Isolates all vendor-specific logic here.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class GeminiClientAdapter implements AiModelClient {

    private final GeminiProperties properties;

    @Override
    public AiModelResponse generateImage(AiModelRequest request) {
        long start = System.currentTimeMillis();

        try (Client client = Client.builder().apiKey(properties.getApiKey()).build()) {
            GenerateContentConfig config = GenerateContentConfig.builder()
                    .responseModalities(Arrays.asList("TEXT", "IMAGE"))
                    .build();

            GenerateContentResponse response = client.models.generateContent(
                    request.getModel(),
                    request.getPrompt(),
                    config);

            for (Part part : response.parts()) {
                if (part.inlineData().isPresent()) {
                    var blob = part.inlineData().get();
                    if (blob.data().isPresent()) {
                        byte[] imageData = blob.data().get();
                        return AiModelResponse.builder()
                                .imageData(imageData)
                                .mimeType(blob.mimeType().isPresent() ? blob.mimeType().get() : "image/png")
                                .processingTimeMs(System.currentTimeMillis() - start)
                                .build();
                    }
                }
            }

            // If no image is returned, throw an exception
            throw new GeminiApiException("No image returned from Gemini API");

        } catch (Exception e) {
            if (e instanceof GeminiApiException) {
                throw (GeminiApiException) e;
            }
            log.error("Gemini API call failed", e);
            throw new GeminiApiException("Gemini API call failed: " + (e.getMessage() != null ? e.getMessage() : ""));
        }
    }
}
