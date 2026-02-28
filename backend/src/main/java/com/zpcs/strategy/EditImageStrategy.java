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
public class EditImageStrategy implements GenerationStrategy {

    private final AiModelClient aiModelClient;

    @Override
    public boolean supports(OperationMode mode) {
        return mode == OperationMode.EDIT_EXISTING;
    }

    @Override
    public GenerationResult execute(GenerationContext ctx) {
        log.info("Executing EDIT_EXISTING strategy for id={}", ctx.getId());
        AiModelRequest aiRequest = AiModelRequest.builder()
                .prompt(ctx.getPrompt())
                .model(ctx.getAiConfig().getModel())
                .thinkingLevel(ctx.getAiConfig().getThinkingLevel())
                .aspectRatio(ctx.getAiConfig().getAspectRatio())
                .width(ctx.getAiConfig().getImageWidth())
                .height(ctx.getAiConfig().getImageHeight())
                .sourceImage(ctx.getSourceImageBytes())
                .build();

        AiModelResponse response = aiModelClient.generateImage(aiRequest)
                .block(Duration.ofSeconds(30));

        return GenerationResult.builder()
                .imageData(response.getImageData())
                .mimeType(response.getMimeType())
                .build();
    }
}
