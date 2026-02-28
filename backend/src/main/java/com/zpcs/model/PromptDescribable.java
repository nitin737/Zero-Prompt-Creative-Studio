package com.zpcs.model;

/**
 * Interface for enums that can describe themselves as prompt fragments.
 * Each enum value provides its own prompt text — no external mapper needed (SRP).
 */
public interface PromptDescribable {
    String getPromptFragment();
}
