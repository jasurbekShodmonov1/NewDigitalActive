package com.example.DigitalAssetNewFeatures.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.Duration;

@Configuration
@EnableCaching
public class InMemoryCacheConfig {

    private static final long CACHE_EXPIRE_IN_HOURS = 23;

    @Bean
    public Cache<String, String> tokenCache() {
        return Caffeine.newBuilder().expireAfterWrite(Duration.ofHours(CACHE_EXPIRE_IN_HOURS)).build();
    }
}
