package com.example.keycloak.config;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.KeycloakBuilder;

//@Configuration
public class KCResteasyProvider {

    //    @Value("${keycloak.client.serverURL}")
    public String keycloakServerURL;
    //    @Value("${keycloak.client.realm}")
    public String keycloakRealm;
    //    @Value("${keycloak.client.clientId}")
    public String keycloakClientID;
    //    @Value("${keycloak.client.clientSecret}")
    public String keycloakClientSecret;

    public KeycloakBuilder kcGetTokenWithClientCredentials() {
        return KeycloakBuilder.builder()
                .realm(keycloakRealm)
                .serverUrl(keycloakServerURL)
                .clientId(keycloakClientID)
                .clientSecret(keycloakClientSecret)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS);
    }

}
