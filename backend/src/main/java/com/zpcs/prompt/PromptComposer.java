package com.zpcs.prompt;

import com.zpcs.dto.request.GenerateImageRequest;

/**
 * Interface for composing a prompt string from a generation request.
 * DIP: Controllers/Orchestrator depend on this interface, not concrete classes.
 */
public interface PromptComposer {
    String compose(GenerateImageRequest request);
}
