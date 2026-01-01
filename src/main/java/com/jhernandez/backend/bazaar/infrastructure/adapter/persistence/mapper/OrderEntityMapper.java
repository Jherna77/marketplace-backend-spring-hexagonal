package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.Order;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.OrderEntity;

/*
 * Mapper interface for converting between Order domain model and OrderEntity.
 * Uses MapStruct for automatic implementation generation.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = { ItemEntityMapper.class })
public interface OrderEntityMapper {

    @Mapping(target = "customer.shopProducts", ignore = true)
    @Mapping(target = "customer.cart", ignore = true)
    @Mapping(target = "customer.favProducts", ignore = true)
    @Mapping(target = "shop.shopProducts", ignore = true)
    @Mapping(target = "shop.cart", ignore = true)
    @Mapping(target = "shop.favProducts", ignore = true)
    OrderEntity toEntity (Order order);

    @Mapping(target = "customer.shopProducts", ignore = true)
    @Mapping(target = "customer.cart", ignore = true)
    @Mapping(target = "customer.favProducts", ignore = true)
    @Mapping(target = "shop.shopProducts", ignore = true)
    @Mapping(target = "shop.cart", ignore = true)
    @Mapping(target = "shop.favProducts", ignore = true)
    Order toDomain(OrderEntity orderEntity);

    List<Order> toDomain(List<OrderEntity> orderEntities);

}
