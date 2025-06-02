package com.example.DigitalAssetNewFeatures.service;


import com.example.DigitalAssetNewFeatures.config.WebClientConfig;
import com.example.DigitalAssetNewFeatures.dto.AssetTypeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AssetTypeService {

    private final AuthClientService authClientService;
    private final WebClient webClient = new WebClientConfig().getClient();

    public Flux<AssetTypeResponseDto> getAllAssetTypes(){
        String token = authClientService.getAccessToken();
        if (Objects.isNull(token) || token.isEmpty() || "0".equals(token)) {
            throw new IllegalStateException("Invalid or null access token");
        }
        return webClient.get()
                .uri("http://localhost:8080/api/types/v1")
                .header("Authorization", "Bearer "+ token)
                .retrieve()
                .bodyToFlux(AssetTypeResponseDto.class)
                .onErrorResume(e -> Flux.error(new RuntimeException("Error fetching asset type: " + e.getMessage())));
    }
}
