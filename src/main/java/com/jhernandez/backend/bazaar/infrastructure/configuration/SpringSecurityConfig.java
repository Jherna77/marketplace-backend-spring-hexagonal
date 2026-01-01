package com.jhernandez.backend.bazaar.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.jhernandez.backend.bazaar.infrastructure.security.CustomAuthenticationEntryPoint;
import com.jhernandez.backend.bazaar.infrastructure.security.JwtAuthenticationFilter;
import com.jhernandez.backend.bazaar.infrastructure.security.JwtValidationFilter;

import lombok.RequiredArgsConstructor;

import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.*;

import java.util.List;

/*
 * Spring Security configuration class.
 * This class configures the security settings for the application, including authentication and authorization.
 */
@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig {

    /* Configuration for JWT tokens:
    *    Secret key used for signing the tokens
    *    Prefix for the token
    *    Header name for authorization
    */
    private final AuthenticationConfiguration authenticationConfiguration;
    private final JwtConfig jwtConfig;
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;

    @Qualifier("handlerExceptionResolver")
    private final HandlerExceptionResolver handlerExceptionResolver;

    /*
     * AuthenticationManager bean that provides authentication capabilities.
     * It is used to authenticate users based on their credentials.
     */
    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /*
     * PasswordEncoder bean that uses BCryptPasswordEncoder for hashing passwords.
     * This is used for encoding passwords before storing them in the database.
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
     * SecurityFilterChain bean that configures the security filter chain for the application.
     * It specifies the authorization rules for different endpoints and adds JWT filters for authentication and validation.
     * Public endpoints:
     *      - GET /swagger-ui/**
     *      - GET /swagger-ui.html
     *      - GET /v3/api-docs/**
     *      - GET /ping
     *      - GET /api/users/{id}
     *      - GET /api/roles
     *      - GET /api/categories/enabled
     *      - GET /api/categories/{id}
     *      - GET /api/categories/random
     *      - GET /api/products/enabled
     *      - GET /api/products/{id}
     *      - GET /api/products/user/{userId}
     *      - GET /api/products/category/{categoryId}
     *      - GET /api/products/search/{name}
     *      - GET /api/products/discounted
     *      - GET /api/products/recent
     *      - GET /api/products/top-selling
     *      - GET /api/products/top-rated
     *      - GET /api/images/{filename:.+}
     *      - GET /api/reviews/product/{productId}
     *      - POST /api/users/register
     * 
     * All other endpoints require authentication.
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests((authorizeRequests) -> authorizeRequests
            .requestMatchers(HttpMethod.GET,
                SWAGGER_UI,
                SWAGGER_UI_HTML,
                API_DOCS,
                PING,
                USER_ID,
                ROLES,
                CATEGORIES_ENABLED,
                CATEGORY_ID,
                CATEGORIES_RANDOM,
                PRODUCTS_ENABLED,
                PRODUCT_ID,
                PRODUCTS_USER_ID,
                PRODUCTS_CATEGORY_ID,
                PRODUCTS_SEARCH_NAME,
                PRODUCTS_DISCOUNTED,
                PRODUCTS_RECENT,
                PRODUCTS_TOP_SELLING,
                PRODUCTS_TOP_RATED,
                IMAGE_ID,
                REVIEW_PRODUCT_ID)
                .permitAll()
            .requestMatchers(HttpMethod.POST, REGISTER).permitAll()
            .anyRequest().authenticated())
            .addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtConfig, handlerExceptionResolver))
            .addFilter(new JwtValidationFilter(authenticationManager(), jwtConfig))
            .csrf(config -> config.disable()) // Disable CSRF to avoid vulnerabilities
            .cors(config -> config.configurationSource(corsConfigurationSource())) // Enable CORS to allow cross-origin requests
            .exceptionHandling(exception -> exception
                .authenticationEntryPoint(authenticationEntryPoint)) // Custom entry point for authentication errors
            .sessionManagement(management ->
                management.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless session to handle authentication in token
            .build();
    }

    /*
     * CORS configuration to allow cross-origin requests.
     * This is necessary for the frontend to communicate with the backend.
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(List.of(ALLOWED_ORIGINS));
        config.setAllowedMethods(List.of(ALLOWED_METHODS));
        config.setAllowedHeaders(List.of(ALLOWED_HEADERS));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> corsRegistrationBean = new FilterRegistrationBean<>(
            new CorsFilter(corsConfigurationSource()));
        corsRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE); // Set the order of the filter to be the first one
        return corsRegistrationBean;
    }

}
