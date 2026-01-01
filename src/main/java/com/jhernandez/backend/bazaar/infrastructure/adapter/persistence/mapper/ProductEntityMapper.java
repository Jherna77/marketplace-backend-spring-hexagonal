package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.Product;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.ProductEntity;

/*
 * Mapper interface for converting between Product domain model and ProductEntity.
 * Uses MapStruct for automatic implementation generation.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {CategoryEntityMapper.class})
public interface ProductEntityMapper {

    @Mapping(target = "shop.shopProducts", ignore = true)
    @Mapping(target = "shop.cart", ignore = true)
    @Mapping(target = "shop.favProducts", ignore = true)
    Product toDomain(ProductEntity productEntity);

    @Mapping(target = "shop.shopProducts", ignore = true)
    @Mapping(target = "shop.cart", ignore = true)
    @Mapping(target = "shop.favProducts", ignore = true)
    ProductEntity toEntity(Product product);
}
