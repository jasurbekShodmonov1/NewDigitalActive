package com.example.DigitalAssetNewFeatures.service;

import com.github.benmanes.caffeine.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

import static com.example.DigitalAssetNewFeatures.utils.ApplicationConstants.TOKEN_CACHE_NAME;

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
        return token;
    }

    private String getTokenFromRemote(){
        //fixme do actual realization
        return "";
    }
}
