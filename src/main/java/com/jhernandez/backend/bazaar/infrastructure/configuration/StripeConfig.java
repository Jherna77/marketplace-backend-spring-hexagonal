package com.jhernandez.backend.bazaar.infrastructure.configuration;

import com.stripe.Stripe;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/*
 * Configuration class for setting up Stripe API key.
 */
@Configuration
public class StripeConfig {

    @Value("${stripe.apiKey}")
    private String apiKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = apiKey;
    }
}
