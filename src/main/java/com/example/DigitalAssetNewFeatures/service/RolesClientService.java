package com.example.DigitalAssetNewFeatures.service;

import com.example.DigitalAssetNewFeatures.model.Role;
import com.example.DigitalAssetNewFeatures.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RolesClientService {

    private final AuthClientService authClientService;
    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.roles-url}")
    private String rolesUrl;

    public Role[] getAllRole(){
        try {
            String token = authClientService.getAccessToken();
            if (token == null) {
                throw new RuntimeException("Access token is null");
            }
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + token);
            HttpEntity<Void> entity = new HttpEntity<>(headers);
            ResponseEntity<Role[]> response = restTemplate.exchange(
                    rolesUrl,
                    HttpMethod.GET,
                    entity,
                    Role[].class
            );

            Role[] roles = response.getBody();
            return Objects.isNull(roles) ? new Role[0] : roles;
        }catch (HttpClientErrorException | HttpServerErrorException e){
            throw new RuntimeException("Failed to get role:"+e.getStatusCode()+" - " + e.getResponseBodyAsString(),e);
        }catch (Exception e){
            throw new RuntimeException("Unexpected error while getting role:"+e.getMessage(),e);
        }
    }

    public Role createRole(Role role){
        try {
            String token = authClientService.getAccessToken();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + token);
            HttpEntity<Role> entity = new HttpEntity<>(role,headers);

            return restTemplate.postForObject(rolesUrl, entity, Role.class);


        }catch (HttpClientErrorException | HttpServerErrorException e){
            throw new RuntimeException("Failed to create role:"+e.getStatusCode()+" - " + e.getResponseBodyAsString(),e);
        }catch (Exception e){
            throw new RuntimeException("Unexpected error while creating role:"+e.getMessage(),e);
        }
    }


}
