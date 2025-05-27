package com.example.DigitalAssetNewFeatures.job;

import com.example.DigitalAssetNewFeatures.service.AuthClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenAcquirerJob {
    private final AuthClientService authClientService;


    @Scheduled(cron = "")
    public void refreshAccessToken(){
        authClientService.getAccessToken();
    }
}
