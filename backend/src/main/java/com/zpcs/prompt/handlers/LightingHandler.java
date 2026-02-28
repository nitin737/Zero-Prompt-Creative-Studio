package com.zpcs.prompt.handlers;

import com.zpcs.dto.request.GenerateImageRequest;
import com.zpcs.prompt.PromptBuilder;
import com.zpcs.prompt.PromptFragmentHandler;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class LightingHandler implements PromptFragmentHandler {

    @Override
    public int getOrder() {
        return 2;
    }

    @Override
    public void handle(GenerateImageRequest request, PromptBuilder builder) {
        if (request.getLighting() != null) {
            builder.append("illuminated with " + request.getLighting().getPromptFragment() + " lighting");
        }
    }
}
