package com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.Review;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.ReviewDto;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper.helper.DateMapper;

/*
 * Mapper interface for converting Review domain models to ReviewDto objects.
 * Uses MapStruct for automatic mapping generation.
 * Includes custom date formatting via DateMapper.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = { DateMapper.class })
public interface ReviewDtoMapper {

    @Mapping(source = "reviewDate", target = "reviewDate", qualifiedByName = "formatDate")
    @Mapping(source = "order.id", target = "orderId")
    ReviewDto toDto(Review review);

    @Mapping(target = "order", expression = "java(new Order(reviewDto.getOrderId()))")
    @Mapping(target = "reviewDate", ignore = true)
    Review toDomain(ReviewDto reviewDto);

}
