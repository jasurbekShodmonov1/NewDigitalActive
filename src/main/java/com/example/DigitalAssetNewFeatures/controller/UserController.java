package com.example.DigitalAssetNewFeatures.controller;

import com.example.DigitalAssetNewFeatures.dto.UserResponseDto;
import com.example.DigitalAssetNewFeatures.model.Role;
import com.example.DigitalAssetNewFeatures.model.User;
import com.example.DigitalAssetNewFeatures.service.UsersClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UsersClientService usersClientService;
    @Operation(summary = "Create a new user", description = "Sends a POST request to create a user via an external API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Role.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error", content = @Content)
    })

    @GetMapping()
    public ResponseEntity<Page<UserResponseDto>>getAllUser(@RequestParam (defaultValue = "0") int page){
        return  usersClientService.getAllUsers(page);
    }


    @PostMapping("/create")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User createdUser = usersClientService.creteUser(user);
        return ResponseEntity.ok(createdUser);
    }

}
