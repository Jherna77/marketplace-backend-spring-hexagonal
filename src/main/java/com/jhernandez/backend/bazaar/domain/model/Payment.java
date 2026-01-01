package com.jhernandez.backend.bazaar.domain.model;

/*
 * Model class representing a payment entity.
 */
public class Payment {

    Double amount;
    String currency;
    String clientSecret;

    public Payment(Double amount, String currency, String clientSecret) {
        this.amount = amount;
        this.currency = currency;
        this.clientSecret = clientSecret;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
    
}
