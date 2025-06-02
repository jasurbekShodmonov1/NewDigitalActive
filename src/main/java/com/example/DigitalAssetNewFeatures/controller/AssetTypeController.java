package com.example.DigitalAssetNewFeatures.controller;

import com.example.DigitalAssetNewFeatures.dto.AssetTypeResponseDto;
import com.example.DigitalAssetNewFeatures.service.AssetTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.io.Flushable;

@RestController
@RequestMapping("/api/asset-types")
@RequiredArgsConstructor
public class AssetTypeController {

    private final AssetTypeService assetTypeService;

    @GetMapping
    public Flux<AssetTypeResponseDto> getAllAssetTypes(){
        return assetTypeService.getAllAssetTypes();
    }


}
