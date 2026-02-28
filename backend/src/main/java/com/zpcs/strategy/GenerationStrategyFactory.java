package com.zpcs.strategy;

import com.zpcs.model.enums.OperationMode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Factory that resolves the correct strategy from the OperationMode at runtime.
 * All strategies are auto-injected via Spring DI.
 */
@Component
@RequiredArgsConstructor
public class GenerationStrategyFactory {

    private final List<GenerationStrategy> strategies;

    public GenerationStrategy getStrategy(OperationMode mode) {
        return strategies.stream()
                .filter(s -> s.supports(mode))
                .findFirst()
                .orElseThrow(() -> new UnsupportedOperationException(
                        "No strategy registered for mode: " + mode));
    }
}
