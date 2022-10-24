package com.jkelany.todo.api.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface TokenService {

    String TOKEN_TYPE_BEARER = "Bearer ";

    String getToken(UserDetails user);

    String getUsernameFromToken(String token) throws Exception;

    boolean isValidToken(String jwtToken) throws Exception;

}
