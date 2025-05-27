package com.example.DigitalAssetNewFeatures.service;

import com.example.DigitalAssetNewFeatures.model.Role;
import com.example.DigitalAssetNewFeatures.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

@Service
public class RolesClientService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.roles-url}")
    private String rolesUrl;

    public Role[] getAllRole(){
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
//            headers.set("Authorization", "Bearer your_token_here");
            HttpEntity<Void> entity = new HttpEntity<>(headers);
            return restTemplate.getForObject(rolesUrl,  Role[].class);
        }catch (HttpClientErrorException | HttpServerErrorException e){
            throw new RuntimeException("Failed to get role:"+e.getStatusCode()+" - " + e.getResponseBodyAsString(),e);
        }catch (Exception e){
            throw new RuntimeException("Unexpected error while getting role:"+e.getMessage(),e);
        }
    }

    public Role createRole(Role role){
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
//            headers.set("Authorization", "Bearer your_token_here");
            HttpEntity<Role> entity = new HttpEntity<>(role,headers);
            return restTemplate.postForObject(rolesUrl, entity, Role.class);
        }catch (HttpClientErrorException | HttpServerErrorException e){
            throw new RuntimeException("Failed to create role:"+e.getStatusCode()+" - " + e.getResponseBodyAsString(),e);
        }catch (Exception e){
            throw new RuntimeException("Unexpected error while creating role:"+e.getMessage(),e);
        }
    }


}
