package com.zpcs.strategy;

import com.zpcs.model.GenerationContext;
import com.zpcs.model.GenerationResult;
import com.zpcs.model.enums.OperationMode;

/**
 * Strategy interface for image generation — one implementation per
 * OperationMode.
 * OCP: New modes are added by creating a new class, not modifying existing
 * code.
 * LSP: All implementations are interchangeable.
 */
public interface GenerationStrategy {
    boolean supports(OperationMode mode);

    GenerationResult execute(GenerationContext context);
}
