package com.zpcs.prompt;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder for fluent prompt construction.
 * Handlers append fragments; build() assembles the final prompt string.
 */
public class PromptBuilder {

    private final StringBuilder subject = new StringBuilder();
    private final List<String> fragments = new ArrayList<>();

    public PromptBuilder subject(String text) {
        subject.append(text);
        return this;
    }

    public PromptBuilder append(String fragment) {
        fragments.add(fragment);
        return this;
    }

    public String build() {
        if (fragments.isEmpty())
            return subject.toString();
        return subject + ", " + String.join(", ", fragments);
    }
}
