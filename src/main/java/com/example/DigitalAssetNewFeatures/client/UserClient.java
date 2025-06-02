package com.example.DigitalAssetNewFeatures.client;

import com.example.DigitalAssetNewFeatures.dto.UserResponseDto;
import com.example.DigitalAssetNewFeatures.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "userClient", url = "${api.url}/api/users/v1")
public interface UserClient {

    @GetMapping
    UserResponseDto getAllUsers();
}
