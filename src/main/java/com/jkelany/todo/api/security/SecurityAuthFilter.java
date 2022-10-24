package com.jkelany.todo.api.security;

import com.jkelany.todo.api.config.AppProperties;
import com.jkelany.todo.api.service.TokenService;
import com.jkelany.todo.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityAuthFilter extends OncePerRequestFilter {

    @Autowired
    private AppProperties appProperties;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwtTokenHeader = request.getHeader(appProperties.getJwtToken().getHeaderKey());
            if (StringUtils.hasLength(jwtTokenHeader) && jwtTokenHeader.startsWith(TokenService.TOKEN_TYPE_BEARER)) {
                String jwtToken = jwtTokenHeader.substring(TokenService.TOKEN_TYPE_BEARER.length());
                String username = tokenService.getUsernameFromToken(jwtToken);
                if (username != null) {
                    UserDetails userDetails = userService.loadUserByUsername(username);
                    if (tokenService.isValidToken(jwtToken)) {
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        filterChain.doFilter(request, response);
    }

}
