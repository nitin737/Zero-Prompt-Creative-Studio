package com.zpcs.config;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "async")
@Validated
@Data
public class AsyncProperties {
    @Min(1)
    @Max(100)
    private int corePoolSize;

    @Min(1)
    @Max(200)
    private int maxPoolSize;

    @Min(1)
    @Max(1000)
    private int queueCapacity;

    @NotBlank
    private String threadNamePrefix;
}
