package com.example.keycloak.config;

import com.example.keycloak.dto.keycloak.request.KCClientCredentialsGrantReq;
import com.example.keycloak.dto.keycloak.request.KCPasswordGrantReq;
import org.keycloak.OAuth2Constants;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Component
public class KCRestTemplateProvider {

    MultiValueMap<String, String> keycloakMapValues;

    public MultiValueMap<String, String> kcPasswordGrantTokenProvider(
            KCPasswordGrantReq request
    ) {
        keycloakMapValues = new LinkedMultiValueMap<>();
        keycloakMapValues.add("username", request.getUsername());
        keycloakMapValues.add("password", request.getPassword());
        keycloakMapValues.add("client_id", request.getClientId());
        keycloakMapValues.add("client_secret", request.getClientSecret());
        keycloakMapValues.add("grant_type", OAuth2Constants.PASSWORD);
        return keycloakMapValues;
    }

    public MultiValueMap<String, String> kcClientCredentialsTokenProvider(
            KCClientCredentialsGrantReq kcClientCredentialsGrantReq
    ) {
        keycloakMapValues = new LinkedMultiValueMap<>();
        keycloakMapValues.add("client_id", kcClientCredentialsGrantReq.getClientId());
        keycloakMapValues.add("client_secret", kcClientCredentialsGrantReq.getClientSecret());
        keycloakMapValues.add("grant_type", OAuth2Constants.CLIENT_CREDENTIALS);
        return keycloakMapValues;
    }
}