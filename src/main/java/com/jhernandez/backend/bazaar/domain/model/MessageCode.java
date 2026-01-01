package com.jhernandez.backend.bazaar.domain.model;

/**
 * Enum representing different message codes used in the application.
 */
public enum MessageCode {

    ORDER_CREATED_SELLER("order.created.seller"),
    ORDER_CREATED_CUSTOMER("order.created.customer"),
    ORDER_UPDATED("order.updated");

    private final String code;

    MessageCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
