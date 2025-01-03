package com.campasklad.facility.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    private static final String BASE_URL = "http://localhost:8080/api/product";

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder  builder) {
        return builder
                .rootUri(BASE_URL)
                .defaultHeader("Accept", "application/json")
                .build();
    }
}
