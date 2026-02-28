package com.zpcs.config;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "gemini")
@Validated
@Data
public class GeminiProperties {
    @NotBlank
    private String apiKey;

    @NotBlank
    private String model;

    @Min(5)
    @Max(120)
    private int timeoutSeconds;

    @Min(0)
    @Max(5)
    private int maxRetries;
}
