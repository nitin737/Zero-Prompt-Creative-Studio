package com.zpcs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ZpcsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZpcsApplication.class, args);
    }
}
