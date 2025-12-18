package com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto;

import lombok.Getter;
import lombok.Setter;

/*
 * Data Transfer Object for payment request information.
 */
@Getter
@Setter
public class PaymentRequestDto {

    private Double amount;
    private String currency;

}
