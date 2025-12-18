package com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.Product;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.ProductDto;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper.helper.NameDisabler;

/*
 * Mapper interface for converting Product domain models to ProductDto objects.
 * Uses MapStruct for automatic mapping generation.
 * Includes custom name adjustment via NameDisabler.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = { CategoryDtoMapper.class, UserDtoMapper.class, NameDisabler.class })
public interface ProductDtoMapper {

    @Mapping(target = "name", source = ".", qualifiedByName = "adjustProductName")    
    @Mapping(source = "shop.id", target = "shopId")
    ProductDto toDto(Product product);

    @Mapping(target = "shop", expression = "java(new User(productDto.getShopId()))")
    @Mapping(target = "createdAt", ignore = true)
    Product toDomain(ProductDto productDto);

    List<ProductDto> toDtoList(List<Product> products);

}
