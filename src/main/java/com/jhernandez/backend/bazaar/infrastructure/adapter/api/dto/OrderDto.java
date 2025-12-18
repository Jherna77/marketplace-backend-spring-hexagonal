package com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto;

import com.jhernandez.backend.bazaar.domain.model.OrderStatus;

import lombok.Getter;
import lombok.Setter;

/*
 * Data Transfer Object for order information.
 */
@Getter
@Setter
public class OrderDto {

    private Long id;
    private OrderStatus status;
    private ItemDto item;
    private Long customerId;
    private Long shopId;
    private String orderDate;

}
