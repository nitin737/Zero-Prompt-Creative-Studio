package com.zpcs.client;

import com.zpcs.config.GeminiProperties;
import com.zpcs.exception.GeminiApiException;
import com.zpcs.exception.QuotaExceededException;
import com.zpcs.model.AiModelRequest;
import com.zpcs.model.AiModelResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.*;

/**
 * Adapter Pattern: adapts the external Gemini API into our internal
 * AiModelResponse.
 * Isolates all vendor-specific logic here.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class GeminiClientAdapter implements AiModelClient {

    private final WebClient webClient;
    private final GeminiProperties properties;

    private static final String API_URL = "https://generativelanguage.googleapis.com/v1beta/models/{model}:generateContent";

    @Override
    public Mono<AiModelResponse> generateImage(AiModelRequest request) {
        long start = System.currentTimeMillis();
        Map<String, Object> body = buildGeminiRequestBody(request);

        return webClient.post()
                .uri(API_URL, request.getModel())
                .header("x-goog-api-key", properties.getApiKey())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve()
                .onStatus(status -> status.value() == 429, resp -> Mono.error(new QuotaExceededException(60)))
                .onStatus(HttpStatusCode::is4xxClientError, resp -> resp.bodyToMono(String.class)
                        .flatMap(b -> Mono.error(new GeminiApiException("Client error: " + b))))
                .onStatus(HttpStatusCode::is5xxServerError,
                        resp -> Mono.error(new GeminiApiException("Gemini API unavailable")))
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
                })
                .map(responseMap -> adaptToAiModelResponse(responseMap, start))
                .timeout(Duration.ofSeconds(properties.getTimeoutSeconds()))
                .doOnError(e -> log.error("Gemini API call failed", e));
    }

    private AiModelResponse adaptToAiModelResponse(Map<String, Object> raw, long start) {
        byte[] imageData = GeminiResponseParser.extractImageBytes(raw);
        return AiModelResponse.builder()
                .imageData(imageData)
                .mimeType("image/png")
                .processingTimeMs(System.currentTimeMillis() - start)
                .build();
    }

    private Map<String, Object> buildGeminiRequestBody(AiModelRequest req) {
        List<Map<String, Object>> parts = new ArrayList<>();
        parts.add(Map.of("text", req.getPrompt()));

        if (req.getSourceImage() != null) {
            parts.add(Map.of("inlineData", Map.of(
                    "mimeType", "image/png",
                    "data", Base64.getEncoder().encodeToString(req.getSourceImage()))));
        }

        Map<String, Object> generationConfig = new LinkedHashMap<>();
        generationConfig.put("responseModalities", List.of("IMAGE", "TEXT"));

        if (req.getAspectRatio() != null) {
            generationConfig.put("imageConfig", Map.of("aspectRatio", req.getAspectRatio()));
        }

        return Map.of(
                "contents", List.of(Map.of("parts", parts)),
                "generationConfig", generationConfig);
    }
}
