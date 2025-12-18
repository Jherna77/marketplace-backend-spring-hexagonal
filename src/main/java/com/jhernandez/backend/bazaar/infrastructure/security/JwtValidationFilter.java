package com.jhernandez.backend.bazaar.infrastructure.security;

import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.CONTENT_TYPE_JSON;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jhernandez.backend.bazaar.infrastructure.configuration.JwtConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * Filter for JWT validation.
 * This filter is responsible for validating the JWT token and setting the authentication in the security context.
 */
public class JwtValidationFilter extends BasicAuthenticationFilter {

    private final JwtConfig jwtConfig;

    public JwtValidationFilter(AuthenticationManager authenticationManager, JwtConfig jwtConfig) {
        super(authenticationManager);
        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String header = request.getHeader(jwtConfig.getHeader());

        // Check if the header is null or does not start with the prefix token
        if (header == null || !header.startsWith(jwtConfig.getPrefix())) {
            chain.doFilter(request, response); // Continue with the filter chain
            return;
        }

        String token = header.replace(jwtConfig.getPrefix(), "");

        try {
            Claims claims = Jwts.parser()
                    .verifyWith(jwtConfig.jwtSecretKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            String email = claims.getSubject();
            var roles = claims.get("roles", List.class);

            @SuppressWarnings("unchecked")
            Collection<GrantedAuthority> authorities = roles.stream()
                    .map(role -> new SimpleGrantedAuthority(role.toString()))
                    .toList();

            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(email, null, authorities));
            chain.doFilter(request, response); // Continue with the filter chain

        } catch (JwtException e) {
            response.setContentType(CONTENT_TYPE_JSON);
            response.setStatus(401);
            new ObjectMapper().writeValue(response.getWriter(), Map.of(
                    "error",
                    e.getMessage()));
        }

    }

}
