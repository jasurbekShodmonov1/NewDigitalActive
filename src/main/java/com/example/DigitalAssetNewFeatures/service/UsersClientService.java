package com.example.DigitalAssetNewFeatures.service;

import com.example.DigitalAssetNewFeatures.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;


@Service
public class UsersClientService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.users-url}")
    private String usersUrl;

    public User creteUser(User user){
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<User> entity = new HttpEntity<>(user,headers);
            return  restTemplate.postForObject(usersUrl, entity, User.class);
        }catch (HttpClientErrorException | HttpServerErrorException e){
            throw new RuntimeException("Failed to create role:"+e.getStatusCode()+" - " + e.getResponseBodyAsString(),e);
        }catch (Exception e){
            throw new RuntimeException("Unexpected error while creating role:"+e.getMessage(),e);
        }
    }
}
