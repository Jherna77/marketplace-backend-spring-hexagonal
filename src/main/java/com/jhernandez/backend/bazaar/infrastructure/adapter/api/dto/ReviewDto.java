package com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto;

import lombok.Getter;
import lombok.Setter;

/*
 * Data Transfer Object for review information.
 */
@Getter
@Setter
public class ReviewDto {

    private Long id;
    private Long orderId;
    private String author;
    private String comment;
    private Integer rating;
    private String reviewDate;

}
