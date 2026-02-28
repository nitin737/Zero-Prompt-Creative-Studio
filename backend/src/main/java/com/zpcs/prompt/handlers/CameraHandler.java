package com.zpcs.prompt.handlers;

import com.zpcs.dto.request.GenerateImageRequest;
import com.zpcs.prompt.PromptBuilder;
import com.zpcs.prompt.PromptFragmentHandler;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
public class CameraHandler implements PromptFragmentHandler {

    @Override
    public int getOrder() {
        return 3;
    }

    @Override
    public void handle(GenerateImageRequest request, PromptBuilder builder) {
        if (request.getCameraComposition() != null) {
            builder.append("shot from a " + request.getCameraComposition().getPromptFragment() + " perspective");
        }
    }
}
