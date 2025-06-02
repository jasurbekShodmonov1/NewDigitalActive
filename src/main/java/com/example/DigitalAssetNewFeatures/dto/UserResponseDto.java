package com.example.DigitalAssetNewFeatures.dto;

import com.example.DigitalAssetNewFeatures.model.User;

import java.util.Set;
import java.util.UUID;

public record UserResponseDto (
         UUID id,
         String firstname,
         String lastname,
         String username,
         String phoneNumber,
         String email,
         String password,
         Set<UUID>roleId

){
}
