package com.zpcs.prompt.handlers;

import com.zpcs.dto.request.GenerateImageRequest;
import com.zpcs.prompt.PromptBuilder;
import com.zpcs.prompt.PromptFragmentHandler;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(0)
public class SubjectHandler implements PromptFragmentHandler {

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public void handle(GenerateImageRequest request, PromptBuilder builder) {
        builder.subject(request.getSubject());
    }
}
