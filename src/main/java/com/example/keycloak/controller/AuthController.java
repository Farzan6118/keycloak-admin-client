package com.example.keycloak.controller;


import com.example.keycloak.dto.keycloak.request.KCClientCredentialsGrantReq;
import com.example.keycloak.dto.keycloak.request.KCPasswordGrantReq;
import com.example.keycloak.dto.keycloak.response.KCClientCredentialsGrantRes;
import com.example.keycloak.dto.keycloak.response.KCPasswordGrantRes;
import com.example.keycloak.service.KCRestTemplateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin(origins = "*", value = "*")
@Tag(name = "keycloak", description = "Keycloak management APIs")
public class AuthController {
    private final KCRestTemplateService keycloakService;

    public AuthController(KCRestTemplateService keycloakService) {
        this.keycloakService = keycloakService;
    }

    @Operation(
            summary = "keycloak password grant token",
            description = "keycloak password grant token",
            tags = {"keycloak"})
    @PostMapping("/get_token_password_grant")
    public ResponseEntity<KCPasswordGrantRes> getTokenPasswordGrantController(
            @RequestBody KCPasswordGrantReq request
    ) {
        return ResponseEntity.ok(keycloakService.getPasswordGrantTokenService(request));
    }

    @Operation(
            summary = "keycloak client credentials",
            description = "keycloak client credentials",
            tags = {"keycloak"})
    @PostMapping("/get_token_client_credentials")
    public ResponseEntity<KCClientCredentialsGrantRes> getTokenClientCredentialsController(
            @RequestBody KCClientCredentialsGrantReq request
    ) {
        return ResponseEntity.ok(keycloakService.getClientCredentialsTokenService(
                request
        ));
    }

}

