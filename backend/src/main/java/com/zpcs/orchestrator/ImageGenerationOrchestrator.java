package com.zpcs.orchestrator;

import com.zpcs.dto.request.EditImageRequest;
import com.zpcs.dto.request.GenerateImageRequest;
import com.zpcs.dto.response.GeneratedImageResponse;
import org.springframework.core.io.Resource;

import java.util.concurrent.CompletableFuture;

/**
 * Single entry point for generation workflows (DIP).
 * Controller depends on this interface, not the concrete orchestrator.
 */
public interface ImageGenerationOrchestrator {
    CompletableFuture<GeneratedImageResponse> generate(GenerateImageRequest request);

    CompletableFuture<GeneratedImageResponse> edit(EditImageRequest request);

    Resource getImageFile(String id);
}
