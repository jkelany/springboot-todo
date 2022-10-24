package com.jkelany.todo.api.service.impl;

import com.jkelany.todo.api.config.AppProperties;
import com.jkelany.todo.api.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private AppProperties appProperties;

    @Override
    public String getToken(UserDetails user) {
        return Jwts.builder().setSubject(user.getUsername()).setIssuedAt(new Date())
                .setExpiration(generateTokenExpirationDate())
                .signWith(SignatureAlgorithm.HS512, appProperties.getJwtToken().getSecret()).compact();
    }

    @Override
    public String getUsernameFromToken(String token) throws Exception {
        return getTokenClaims(token).getSubject();
    }

    @Override
    public boolean isValidToken(String jwtToken) throws Exception {
        return !isTokenExpired(jwtToken);
    }

    private boolean isTokenExpired(String jwtToken) {
        return getTokenClaims(jwtToken).getExpiration().before(new Date());
    }

    private Claims getTokenClaims(String token) {
        return Jwts.parser().setSigningKey(appProperties.getJwtToken().getSecret()).parseClaimsJws(token).getBody();
    }

    private Date generateTokenExpirationDate() {
        return new Date(System.currentTimeMillis() + (appProperties.getJwtToken().getExpiration() * 1000));
    }

}
