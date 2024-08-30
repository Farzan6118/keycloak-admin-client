package com.example.keycloak.dto.keycloak.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KCPasswordGrantReq {
    private String realm;
    private String username;
    private String password;
    private String clientId;
    private String clientSecret;
}