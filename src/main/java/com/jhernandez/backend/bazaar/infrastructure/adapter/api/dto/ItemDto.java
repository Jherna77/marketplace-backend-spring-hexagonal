package com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto;

import lombok.Getter;
import lombok.Setter;

/*
 * Data Transfer Object for item information.
 */
@Getter
@Setter
public class ItemDto {

    private Long id;
    private ProductDto product;
    private Double salePrice;
    private Double saleShipping;
    private Integer quantity;
    private Double totalPrice;

}
