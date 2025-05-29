package com.example.DigitalAssetNewFeatures.service;

import com.example.DigitalAssetNewFeatures.dto.AuthResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

import static com.example.DigitalAssetNewFeatures.utils.ApplicationConstants.TOKEN_CACHE_NAME;

@Service

public class AuthClientService {


    @Autowired
    private RestTemplate restTemplate;

    @Value("${auth.username}")
    private String username;

    @Value("${auth.password}")
    private String password;

    @Value("${auth.login-url}")
    private String loginUrl;

    private final Cache<String, String> tokenCache;

    public AuthClientService(Cache<String, String> tokenCache) {
        this.tokenCache = tokenCache;
    }

    public String getAccessToken(){
        var token = tokenCache.getIfPresent(TOKEN_CACHE_NAME);
        if(Objects.isNull(token)){
            token=getTokenFromRemote();
            tokenCache.put(TOKEN_CACHE_NAME,token);
        }

        System.out.println(token);
        return token;
    }

    private String getTokenFromRemote(){
        //fixme do actual realization
        try {
            HttpClient client = HttpClient.newHttpClient();

            ObjectMapper mapper = new ObjectMapper();
            String jsonPayload = mapper.writeValueAsString(
                    new AuthResponseDto(username, password)
            );

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(loginUrl))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                    .build();

            HttpResponse<String> response = client.send(
                    request, HttpResponse.BodyHandlers.ofString()
            );
            if (response.statusCode() == 200) {
                // Assuming the response is JSON with a "token" field
                String responseBody = response.body();
                // Parse JSON to extract token (example assumes {"token": "value"})
                String token = mapper.readTree(responseBody).get("token").asText();
                if (token == null || token.isEmpty() || "0".equals(token)) {
                    throw new IllegalStateException("Invalid token received: " + token);
                }
                return token;
            } else {
                throw new RuntimeException("Failed to get token, status: " + response.statusCode());
            }
        }catch (Exception e) {
            throw new RuntimeException("Error fetching token from remote: " + e.getMessage(), e);
        }
    }
}
