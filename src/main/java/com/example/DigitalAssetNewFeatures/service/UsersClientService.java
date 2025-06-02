package com.example.DigitalAssetNewFeatures.service;

import com.example.DigitalAssetNewFeatures.client.UserClient;
import com.example.DigitalAssetNewFeatures.config.RestClientConfig;
import com.example.DigitalAssetNewFeatures.dto.PageResponse;
import com.example.DigitalAssetNewFeatures.dto.UserResponseDto;
import com.example.DigitalAssetNewFeatures.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;




@Service
@RequiredArgsConstructor
public class UsersClientService {

    private final UserClient userClient;
    private final AuthClientService authClientService;
    private final RestClient restClient =  new RestClientConfig().getClient();;
    @Autowired
    private RestTemplate restTemplate;



    @Value("${api.url}/api/users/v1")
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

    public ResponseEntity<Page<UserResponseDto>> getAllUsers(int page){
        try{
            String token = authClientService.getAccessToken();
            if (Objects.isNull(token) || token.isEmpty() || "0".equals(token)) {
                throw new IllegalStateException("Invalid or null access token");
            }

            PageResponse<UserResponseDto> users = restClient.get()
                    .uri("/v1")
                    .header("Authorization", "Bearer "+ token)
                    .retrieve()
                    .body(new ParameterizedTypeReference<PageResponse<UserResponseDto>>() {});;

            List<UserResponseDto> userList = users.getContent();
            System.out.println(userList);
            int start = Math.min(page * 2, userList.size());
            int end = Math.min(start + 2, userList.size());
            List<UserResponseDto> pagedUsers = userList.subList(start, end);

            Page<UserResponseDto> userPage = new PageImpl<>(
                    pagedUsers,
                    PageRequest.of(page,2),
                    userList.size()
            );
            System.out.println(userPage);

            return ResponseEntity.ok(userPage);
        }catch (Exception e) {
            throw new RuntimeException( e.getMessage(), e);
        }
    }
}
