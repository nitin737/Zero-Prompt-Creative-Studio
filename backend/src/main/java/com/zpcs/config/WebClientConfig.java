package com.zpcs.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {

        private final WebClientProperties properties;

        @Bean
        public WebClient webClient() {
                // Increase buffer size for image responses
                ExchangeStrategies strategies = ExchangeStrategies.builder()
                                .codecs(configurer -> configurer.defaultCodecs()
                                                .maxInMemorySize(properties.getMaxInMemorySize()))
                                .build();

                return WebClient.builder()
                                .exchangeStrategies(strategies)
                                .build();
        }
}
