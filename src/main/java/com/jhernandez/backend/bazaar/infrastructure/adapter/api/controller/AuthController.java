package com.jhernandez.backend.bazaar.infrastructure.adapter.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.VALIDATE_TOKEN;
import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.PING;

/*
 * Controller for authentication-related endpoints.
 */
@RestController
@RequestMapping
@CrossOrigin(originPatterns = "*")
@Slf4j
public class AuthController {

    @GetMapping(VALIDATE_TOKEN)
    public ResponseEntity<?> validateToken(HttpServletRequest request) {
        log.info("Token is valid");
        // Token authenticated by the filter
        return ResponseEntity.ok().build();
    }

    @GetMapping(PING)
    public ResponseEntity<String> ping() {
        log.info("Ping received");
        return ResponseEntity.ok("pong");
    }
}
