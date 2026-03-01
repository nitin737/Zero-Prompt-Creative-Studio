package com.zpcs.client;

import com.zpcs.model.AiModelRequest;
import com.zpcs.model.AiModelResponse;

/**
 * Abstracts the AI provider (DIP).
 * Swap Gemini for OpenAI/Stability by implementing this interface.
 */
public interface AiModelClient {
    AiModelResponse generateImage(AiModelRequest request);
}
