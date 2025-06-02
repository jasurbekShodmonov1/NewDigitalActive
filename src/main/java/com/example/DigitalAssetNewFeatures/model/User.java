package com.example.DigitalAssetNewFeatures.model;

import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class User {

    private UUID id;
    private String firstname;
    private String lastname;
    private String username;
    private String phoneNumber;
    private String email;
    private String password;
    private Set<UUID> roleId;

}
