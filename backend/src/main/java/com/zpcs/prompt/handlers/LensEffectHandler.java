package com.zpcs.prompt.handlers;

import com.zpcs.dto.request.GenerateImageRequest;
import com.zpcs.prompt.PromptBuilder;
import com.zpcs.prompt.PromptFragmentHandler;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(5)
public class LensEffectHandler implements PromptFragmentHandler {

    @Override
    public int getOrder() {
        return 5;
    }

    @Override
    public void handle(GenerateImageRequest request, PromptBuilder builder) {
        if (request.getLensEffect() != null) {
            builder.append("featuring " + request.getLensEffect().getPromptFragment() + " photography techniques");
        }
    }
}
