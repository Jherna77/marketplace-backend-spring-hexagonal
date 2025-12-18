package com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/*
 * Data Transfer Object for product information.
 */
@Getter
@Setter
public class ProductDto {
    
    private Long id;
    private Boolean enabled;
    private String name;
    private String description;
    private Double price;
    private Double shipping;    
    private List<CategoryDto> categories;
    private List<String> imagesUrl;
    private Long shopId;
    private Integer sold;
    private Double rating;
    private Integer ratingCount;
    private Boolean hasDiscount;
    private Double discountPrice;
    private Integer stock;

}

