package com.jkelany.todo.api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@OpenAPIDefinition
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        final String SECURITY_SCHEMA_NAME = "JWT Bearer Auth";
        return new OpenAPI()
                .info(new Info()
                        .title("Todo API")
                        .version("1.0.0")
                        .description("Todo Application API")
                        .termsOfService("https://mkelany.com/terms")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://mkelany.com/license"))
                        .contact(new Contact()
                                .name("Mahmoud Kelany")
                                .email("mahmoudjkelany@gmail.com")
                                .url("https://mkelany.com"))
                )
                .addSecurityItem(new SecurityRequirement()
                        .addList(SECURITY_SCHEMA_NAME, List.of("read", "write")))
                .components(new Components()
                        .addSecuritySchemes(SECURITY_SCHEMA_NAME,
                                new SecurityScheme()
                                        .name(SECURITY_SCHEMA_NAME)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }

    @Bean
    public GroupedOpenApi allGroupedOpenApi() {
        return GroupedOpenApi.builder()
                .group("All API")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public GroupedOpenApi authGroupedOpenApi() {
        return GroupedOpenApi.builder()
                .group("Auth API")
                .pathsToMatch("/auth/**")
                .build();
    }

    @Bean
    public GroupedOpenApi todoGroupedOpenApi() {
        return GroupedOpenApi.builder()
                .group("Todo API")
                .pathsToExclude("/auth/**")
                .build();
    }
}
