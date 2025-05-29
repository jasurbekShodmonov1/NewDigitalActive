package com.example.DigitalAssetNewFeatures.controller;

import com.example.DigitalAssetNewFeatures.model.Role;


import com.example.DigitalAssetNewFeatures.service.RolesClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {


    private final RolesClientService rolesClientService ;


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Role.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error", content = @Content)
    })

    @GetMapping()
    public ResponseEntity<List<Role>>getAllUser(){
        Role[] roles = rolesClientService.getAllRole();
        return ResponseEntity.ok(Arrays.asList(roles));


    }
    @PostMapping("/create")
    public ResponseEntity<Role> createUser(@Valid @RequestBody Role roles) {

        Role createdUser = rolesClientService.createRole(roles);
        return ResponseEntity.ok(createdUser);
    }
}
