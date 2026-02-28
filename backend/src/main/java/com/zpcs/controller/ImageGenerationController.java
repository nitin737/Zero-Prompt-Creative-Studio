package com.zpcs.controller;

import com.zpcs.dto.request.EditImageRequest;
import com.zpcs.dto.request.GenerateImageRequest;
import com.zpcs.dto.response.GeneratedImageResponse;
import com.zpcs.orchestrator.ImageGenerationOrchestrator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/images")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Image Generation")
public class ImageGenerationController {

    private final ImageGenerationOrchestrator orchestrator;

    @PostMapping("/generate")
    @Operation(summary = "Generate a new image from options")
    public CompletableFuture<ResponseEntity<GeneratedImageResponse>> generate(
            @Valid @RequestBody GenerateImageRequest request) {
        log.info("Generate: mode={}, style={}", request.getOperationMode(), request.getAestheticStyle());
        return orchestrator.generate(request).thenApply(ResponseEntity::ok);
    }

    @PostMapping("/edit")
    @Operation(summary = "Edit an existing image")
    public CompletableFuture<ResponseEntity<GeneratedImageResponse>> edit(
            @Valid @RequestBody EditImageRequest request) {
        log.info("Edit: mode={}", request.getOperationMode());
        return orchestrator.edit(request).thenApply(ResponseEntity::ok);
    }

    @GetMapping("/{id}/file")
    @Operation(summary = "Get a generated image file")
    public ResponseEntity<Resource> getFile(@PathVariable String id) {
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(orchestrator.getImageFile(id));
    }
}
