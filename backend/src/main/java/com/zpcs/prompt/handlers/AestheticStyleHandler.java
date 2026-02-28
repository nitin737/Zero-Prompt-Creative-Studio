package com.zpcs.prompt.handlers;

import com.zpcs.dto.request.GenerateImageRequest;
import com.zpcs.prompt.PromptBuilder;
import com.zpcs.prompt.PromptFragmentHandler;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class AestheticStyleHandler implements PromptFragmentHandler {

    @Override
    public int getOrder() {
        return 1;
    }

    @Override
    public void handle(GenerateImageRequest request, PromptBuilder builder) {
        if (request.getAestheticStyle() != null) {
            builder.append("rendered in a " + request.getAestheticStyle().getPromptFragment() + " aesthetic");
        }
    }
}
