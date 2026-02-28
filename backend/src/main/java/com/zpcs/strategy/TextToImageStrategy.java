package com.zpcs.strategy;

import com.zpcs.client.AiModelClient;
import com.zpcs.model.*;
import com.zpcs.model.enums.OperationMode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@RequiredArgsConstructor
@Slf4j
public class TextToImageStrategy implements GenerationStrategy {

    private final AiModelClient aiModelClient;

    @Override
    public boolean supports(OperationMode mode) {
        return mode == OperationMode.TEXT_TO_IMAGE;
    }

    @Override
    public GenerationResult execute(GenerationContext ctx) {
        log.info("Executing TEXT_TO_IMAGE strategy for id={}", ctx.getId());
        AiModelRequest aiRequest = AiModelRequest.builder()
                .prompt(ctx.getPrompt())
                .model(ctx.getAiConfig().getModel())
                .thinkingLevel(ctx.getAiConfig().getThinkingLevel())
                .aspectRatio(ctx.getAiConfig().getAspectRatio())
                .width(ctx.getAiConfig().getImageWidth())
                .height(ctx.getAiConfig().getImageHeight())
                .build();

        AiModelResponse response = aiModelClient.generateImage(aiRequest)
                .block(Duration.ofSeconds(30));

        return GenerationResult.builder()
                .imageData(response.getImageData())
                .mimeType(response.getMimeType())
                .build();
    }
}
