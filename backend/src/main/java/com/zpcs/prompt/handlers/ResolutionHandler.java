package com.zpcs.prompt.handlers;

import com.zpcs.dto.request.GenerateImageRequest;
import com.zpcs.prompt.PromptBuilder;
import com.zpcs.prompt.PromptFragmentHandler;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(6)
public class ResolutionHandler implements PromptFragmentHandler {

    @Override
    public int getOrder() {
        return 6;
    }

    @Override
    public void handle(GenerateImageRequest request, PromptBuilder builder) {
        if (request.getResolution() != null) {
            builder.append("highly detailed, " + request.getResolution().getDisplayName() + " resolution");
        }
    }
}
