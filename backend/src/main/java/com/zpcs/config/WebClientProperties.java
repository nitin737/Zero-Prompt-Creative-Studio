package com.zpcs.config;

import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "webclient")
@Validated
@Data
public class WebClientProperties {
    @Min(1024 * 1024)
    private int maxInMemorySize;
}
