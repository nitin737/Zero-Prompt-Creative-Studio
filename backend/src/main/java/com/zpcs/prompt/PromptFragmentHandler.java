package com.zpcs.prompt;

import com.zpcs.dto.request.GenerateImageRequest;
import org.springframework.core.Ordered;

/**
 * Chain of Responsibility handler — one per dropdown category (SRP, OCP).
 * Each handler appends its fragment to the PromptBuilder.
 */
public interface PromptFragmentHandler extends Ordered {
    void handle(GenerateImageRequest request, PromptBuilder builder);
}
