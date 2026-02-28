package com.zpcs.prompt.handlers;

import com.zpcs.dto.request.GenerateImageRequest;
import com.zpcs.prompt.PromptBuilder;
import com.zpcs.prompt.PromptFragmentHandler;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(4)
public class ColorPaletteHandler implements PromptFragmentHandler {

    @Override
    public int getOrder() {
        return 4;
    }

    @Override
    public void handle(GenerateImageRequest request, PromptBuilder builder) {
        if (request.getColorPalette() != null) {
            builder.append("utilizing a " + request.getColorPalette().getPromptFragment() + " color palette");
        }
    }
}
