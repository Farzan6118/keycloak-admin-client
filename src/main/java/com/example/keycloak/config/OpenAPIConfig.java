package com.example.keycloak.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig implements CommandLineRunner {
    @Value("${server.port}")
    private String port;

    private String prodUrl;
    private String devUrl;

    @Override
    public void run(String... args) {
        prodUrl = "http://localhost:" + port;
        devUrl = "http://localhost:" + port;
    }

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server()
                .url(devUrl)
                .description("Server URL in Development environment");

        Server prodServer = new Server()
                .url(prodUrl)
                .description("Server URL in Production environment");

        Contact contact = new Contact()
                .email("farzan.6118@gmail.com")
                .name("Farzan Saketi")
                .url(devUrl);

        License mitLicense = new License()
                .name("MIT License")
                .url(devUrl);

        Info info = new Info()
                .title("Keycloak client admin sample service API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage tutorials.")
                .termsOfService("https://github.com/Farzan6118/")
                .license(mitLicense);

        Components components = new Components()
                .addSecuritySchemes("BEARER KEY", new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .in(SecurityScheme.In.HEADER));

        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer, prodServer))
//                .components(components)
                ;
    }
}