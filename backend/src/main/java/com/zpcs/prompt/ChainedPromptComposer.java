package com.zpcs.prompt;

import com.zpcs.dto.request.GenerateImageRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Assembles prompt fragment handlers via Spring DI.
 * Spring auto-sorts @Order components — adding a new handler is OCP-compliant.
 */
@Service
@Slf4j
public class ChainedPromptComposer implements PromptComposer {

    private final List<PromptFragmentHandler> handlers;

    public ChainedPromptComposer(List<PromptFragmentHandler> handlers) {
        this.handlers = handlers;
    }

    @Override
    public String compose(GenerateImageRequest request) {
        PromptBuilder builder = new PromptBuilder();
        handlers.forEach(handler -> handler.handle(request, builder));
        String prompt = builder.build();
        log.info("Composed prompt ({} handlers): {}", handlers.size(), prompt);
        return prompt;
    }
}
