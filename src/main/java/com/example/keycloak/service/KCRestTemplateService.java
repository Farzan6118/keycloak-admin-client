package com.example.keycloak.service;


import com.example.keycloak.config.KCRestTemplateProvider;
import com.example.keycloak.dto.keycloak.request.KCClientCredentialsGrantReq;
import com.example.keycloak.dto.keycloak.request.KCPasswordGrantReq;
import com.example.keycloak.dto.keycloak.response.KCClientCredentialsGrantRes;
import com.example.keycloak.dto.keycloak.response.KCPasswordGrantRes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class KCRestTemplateService {
    private final KCRestTemplateProvider keycloakProvider;
    private final RestTemplate restTemplate;
    @Value("${keycloak.client.serverURL}")
    public String kcURL;
    @Value("${keycloak.client.realm}")
    public String realm;

    public KCRestTemplateService(KCRestTemplateProvider keycloakProvider, RestTemplate restTemplate) {
        this.keycloakProvider = keycloakProvider;
        this.restTemplate = restTemplate;
    }


    public KCPasswordGrantRes getPasswordGrantTokenService(
            KCPasswordGrantReq request
    ) {
        // Get the instance with credentials
        MultiValueMap<String, String> instance = keycloakProvider
                .kcPasswordGrantTokenProvider(request);
        // Set the URL for the POST request
        String url = kcURL
                + "/realms/"
                + request.getRealm()
                + "/protocol/openid-connect/token";
        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // Create the request entity
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(instance, headers);

        // Make the POST request
        ResponseEntity<KCPasswordGrantRes> stringResponseEntity =
                restTemplate.postForEntity(url, requestEntity, KCPasswordGrantRes.class);

        // Check for successful response
        if (stringResponseEntity.getStatusCode() == HttpStatus.OK) {
            return stringResponseEntity.getBody(); // Return the token response
        } else {
            // Handle error response
            throw new RuntimeException("Failed to login: " + stringResponseEntity.getStatusCode());
        }
    }

    public KCClientCredentialsGrantRes getClientCredentialsTokenService(
            KCClientCredentialsGrantReq request
    ) {
        // Get the instance with credentials
        MultiValueMap<String, String> instance = keycloakProvider
                .kcClientCredentialsTokenProvider(request);
        // Set the URL for the POST request
        String url = kcURL
                + "/realms/"
                + request.getRealm()
                + "/protocol/openid-connect/token";
        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // Create the request entity
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(instance, headers);

        // Make the POST request
        ResponseEntity<KCClientCredentialsGrantRes> stringResponseEntity =
                restTemplate.postForEntity(url, requestEntity, KCClientCredentialsGrantRes.class);

        // Check for successful response
        if (stringResponseEntity.getStatusCode() == HttpStatus.OK) {
            return stringResponseEntity.getBody(); // Return the token response
        } else {
            // Handle error response
            throw new RuntimeException("Failed to login: " + stringResponseEntity.getStatusCode());
        }
    }

}
