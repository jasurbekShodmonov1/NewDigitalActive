package com.example.DigitalAssetNewFeatures.model;

import lombok.Data;

import java.util.UUID;

@Data

public class Role {

    private UUID id;
    private String name;
    private String description;
//    private Set<Role> roles;
}