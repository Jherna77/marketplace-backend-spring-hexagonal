package com.jhernandez.backend.bazaar.infrastructure.adapter.api.handler;

import com.stripe.exception.StripeException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.jhernandez.backend.bazaar.domain.exception.DomainException;
import com.jhernandez.backend.bazaar.domain.model.ErrorCode;

import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

/*
 * Global exception handler for the application.
 * Handles various custom exceptions and returns localized error messages.
 * Each handler method retrieves the appropriate message from the MessageSource
 * based on the exception's error code and the provided locale.
 * Logs the error message for debugging purposes.
 * Returns a ResponseEntity with a bad request status and the localized error message.
 */
@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    /**
     * Handles DomainException and returns a localized error message.
     *
     * @param ex     the DomainException thrown
     * @param locale the locale for localization
     * @return a ResponseEntity with the error message
     */
    @ExceptionHandler(DomainException.class)
    public ResponseEntity<String> handleDomainException(DomainException ex, Locale locale) {
        String message = messageSource.getMessage(ex.getErrorCode().getCode(), null, locale);
        log.error("{}: {}", ex.getClass().getSimpleName(), message);
        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException ex, Locale locale) {
        String message = messageSource.getMessage(ErrorCode.AUTHENTICATION_CREDENTIALS_INVALID.getCode(), null, locale);
        log.error("AuthenticationException: {}", message);       
        return ResponseEntity.status(401).body(message);
    }

    @ExceptionHandler(StripeException.class)
    public ResponseEntity<String> handleStripeException(StripeException ex, Locale locale) {
        String message = messageSource.getMessage(ex.getCode(), null, locale);
        log.error("StripeException: {}", message);
        return ResponseEntity.badRequest().body(message);
    }
    
}

