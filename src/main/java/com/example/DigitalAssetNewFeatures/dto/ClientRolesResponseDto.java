package com.example.DigitalAssetNewFeatures.dto;


import java.util.UUID;

public record ClientRolesResponseDto(UUID id, String name, String description) {}
//@Data
////@JsonDeserialize(using = RolesResponseDtoDeserializer.class)
//public class RolesResponseDto{
//    private Role[] roles;
//}
