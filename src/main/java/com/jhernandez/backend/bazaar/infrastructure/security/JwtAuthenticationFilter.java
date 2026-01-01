package com.jhernandez.backend.bazaar.infrastructure.security;

import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.CONTENT_TYPE_JSON;

import java.io.IOException;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.LoginRequestDto;
import com.jhernandez.backend.bazaar.infrastructure.configuration.JwtConfig;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * Filter for JWT authentication.
 * This filter is responsible for authenticating the user and generating a JWT token.
 */
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtConfig jwtConfig;
    private final HandlerExceptionResolver resolver;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            LoginRequestDto login = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDto.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            login.email(),
                            login.password()));
        } catch (IOException e) {
            log.error("Error reading JSON from request", e);
            throw new InternalAuthenticationServiceException(e.getMessage(), e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            FilterChain chain, Authentication authResult)
            throws IOException, ServletException {

        User user = (User) authResult.getPrincipal();
        String email = user.getUsername();

        // Get the authorities from the authenticated user
        var roles = user.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .toList();

        // Generate the JWT token
        String token = Jwts.builder()
                .subject(email)
                .claim("roles", roles)
                .signWith(jwtConfig.jwtSecretKey())
                .compact();

        // Add the token to the response header
        response.addHeader(jwtConfig.getHeader(), jwtConfig.getPrefix() + token);        
        response.setContentType(CONTENT_TYPE_JSON);
        response.setStatus(200);
        new ObjectMapper().writeValue(response.getWriter(), Map.of(
            "email", user.getUsername(),
            "token", token
        ));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) {

        resolver.resolveException(request, response, null, failed);

    }

}
