package com.example.DigitalAssetNewFeatures.client;

import com.example.DigitalAssetNewFeatures.config.FeignConfig;
import com.example.DigitalAssetNewFeatures.dto.ClientRolesResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(
        name = "roleClient",
        url = "${api.url}/api/roles/v1",
        configuration = {FeignConfig.class}
)
public interface RoleClient {

    @GetMapping(consumes = "application/json")
    List<ClientRolesResponseDto> getAllRoles();
}
