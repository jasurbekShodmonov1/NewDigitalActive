package com.example.DigitalAssetNewFeatures.config;

import com.example.DigitalAssetNewFeatures.service.AuthClientService;
import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FeignConfig {

    private final AuthClientService authClientService;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            String token = authClientService.getAccessToken();
            if (token != null && !token.isEmpty() && !"0".equals(token)) {
                requestTemplate.header("Authorization", "Bearer " + token);
            }
        };

    }
}

