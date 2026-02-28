package com.zpcs.client;

import com.zpcs.model.AiModelRequest;
import com.zpcs.model.AiModelResponse;
import reactor.core.publisher.Mono;

/**
 * Abstracts the AI provider (DIP).
 * Swap Gemini for OpenAI/Stability by implementing this interface.
 */
public interface AiModelClient {
    Mono<AiModelResponse> generateImage(AiModelRequest request);
}
