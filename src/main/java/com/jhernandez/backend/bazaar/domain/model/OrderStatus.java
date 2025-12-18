package com.jhernandez.backend.bazaar.domain.model;

/*
 * Enumeration representing the different statuses an order can have.
 */
public enum OrderStatus {
    PENDING,
    CONFIRMED,
    SHIPPED,
    DELIVERED,
    CANCELLED,
    RETURNED
}
