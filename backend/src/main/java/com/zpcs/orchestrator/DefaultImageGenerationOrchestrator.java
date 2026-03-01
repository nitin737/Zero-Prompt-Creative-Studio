package com.zpcs.orchestrator;

import com.zpcs.config.GeminiProperties;
import com.zpcs.dto.request.EditImageRequest;
import com.zpcs.dto.request.GenerateImageRequest;
import com.zpcs.dto.response.GeneratedImageResponse;
import com.zpcs.dto.response.ImageMetadata;
import com.zpcs.event.ImageGeneratedEvent;
import com.zpcs.exception.ImageNotFoundException;
import com.zpcs.model.*;
import com.zpcs.model.enums.AspectRatio;
import com.zpcs.model.enums.ResolutionQuality;
import com.zpcs.model.enums.ThinkingLevel;
import com.zpcs.prompt.PromptComposer;
import com.zpcs.storage.ImageRecordRepository;
import com.zpcs.storage.StorageService;
import com.zpcs.strategy.GenerationStrategy;
import com.zpcs.strategy.GenerationStrategyFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultImageGenerationOrchestrator implements ImageGenerationOrchestrator {

        private final GeminiProperties geminiProperties;
        private final PromptComposer promptComposer;
        private final GenerationStrategyFactory strategyFactory;
        private final StorageService storageService;
        private final ImageRecordRepository repository;
        private final ApplicationEventPublisher eventPublisher;

        @Override
        public GeneratedImageResponse generate(GenerateImageRequest request) {
                long startTime = System.currentTimeMillis();
                String imageId = "img_" + UUID.randomUUID().toString().replace("-", "").substring(0, 12);

                // 1. Compose prompt (Chain of Responsibility)
                String prompt = promptComposer.compose(request);

                // 2. Build context
                byte[] sourceBytes = null;
                if (request.getSourceImageBase64() != null) {
                        sourceBytes = Base64.getDecoder().decode(request.getSourceImageBase64());
                }

                GenerationContext context = GenerationContext.builder()
                                .id(imageId)
                                .prompt(prompt)
                                .originalRequest(request)
                                .aiConfig(buildAiConfig(request))
                                .sourceImageBytes(sourceBytes)
                                .build();

                // 3. Resolve & execute strategy (Strategy Pattern)
                GenerationStrategy strategy = strategyFactory.getStrategy(request.getOperationMode());
                GenerationResult result = strategy.execute(context);

                // 4. Persist image (Repository Pattern)
                String filePath = storageService.save(result.getImageData(), imageId);

                long elapsed = System.currentTimeMillis() - startTime;
                ImageRecord record = ImageRecord.builder()
                                .id(imageId)
                                .filePath(filePath)
                                .prompt(prompt)
                                .operationMode(request.getOperationMode())
                                .generationTimeMs(elapsed)
                                .createdAt(LocalDateTime.now())
                                .build();
                repository.save(record);

                // 5. Publish event (Observer Pattern)
                eventPublisher.publishEvent(new ImageGeneratedEvent(this, record));

                // 6. Map to response
                return toResponse(imageId, prompt, elapsed, request);
        }

        @Override
        public GeneratedImageResponse edit(EditImageRequest request) {
                // Convert edit request to generate request for reuse
                GenerateImageRequest generateRequest = GenerateImageRequest.builder()
                                .subject(request.getSubject())
                                .operationMode(request.getOperationMode())
                                .aestheticStyle(request.getAestheticStyle())
                                .lighting(request.getLighting())
                                .styleIntensity(request.getStyleIntensity())
                                .aspectRatio(request.getAspectRatio() != null ? request.getAspectRatio()
                                                : AspectRatio.RATIO_1_1)
                                .resolution(request.getResolution() != null ? request.getResolution()
                                                : ResolutionQuality.STANDARD)
                                .thinkingLevel(request.getThinkingLevel() != null ? request.getThinkingLevel()
                                                : ThinkingLevel.CREATIVE)
                                .sourceImageBase64(request.getSourceImageBase64())
                                .build();
                return generate(generateRequest);
        }

        @Override
        public Resource getImageFile(String id) {
                return repository.findById(id)
                                .map(record -> storageService.load(id))
                                .orElseThrow(() -> new ImageNotFoundException(id));
        }

        private GeminiRequestConfig buildAiConfig(GenerateImageRequest req) {
                return GeminiRequestConfig.builder()
                                .model(geminiProperties.getModel())
                                .thinkingLevel(req.getThinkingLevel())
                                .aspectRatio(req.getAspectRatio().getApiValue())
                                .imageWidth(req.getResolution().getWidth())
                                .imageHeight(req.getResolution().getHeight())
                                .build();
        }

        private GeneratedImageResponse toResponse(String id, String prompt, long elapsed,
                        GenerateImageRequest req) {
                return GeneratedImageResponse.builder()
                                .id(id)
                                .imageUrl("/api/v1/images/" + id + "/file")
                                .prompt(prompt)
                                .generationTimeMs(elapsed)
                                .metadata(ImageMetadata.builder()
                                                .model(geminiProperties.getModel())
                                                .thinkingLevel(req.getThinkingLevel())
                                                .aspectRatio(req.getAspectRatio().getApiValue())
                                                .resolution(req.getResolution().name())
                                                .createdAt(LocalDateTime.now())
                                                .build())
                                .build();
        }
}
