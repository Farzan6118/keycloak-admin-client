package com.example.keycloak.dto.keycloak.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KCClientCredentialsGrantReq {
    private String clientId;
    private String clientSecret;
    private String realm;
}