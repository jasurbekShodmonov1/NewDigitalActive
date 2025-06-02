package com.example.DigitalAssetNewFeatures.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient.Builder restClientBuilder(){
        return RestClient.builder()
                .baseUrl("http://localhost:8080/api/users");

    }

    public RestClient getClient() {
        return restClientBuilder().build();
    }
}
