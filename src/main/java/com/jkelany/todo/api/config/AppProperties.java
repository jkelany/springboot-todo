package com.jkelany.todo.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
@Data
public class AppProperties {

    private JwtToken jwtToken;

    @Data
    public static class JwtToken {
        private long expiration = 604800L;
        private String secret = "randSecret@2022";
        private String headerKey = "Authorization";
    }
}
