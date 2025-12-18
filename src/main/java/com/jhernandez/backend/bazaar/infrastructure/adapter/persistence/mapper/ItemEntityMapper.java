package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.Item;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.ItemEntity;

/*
 * Mapper interface for converting between Item domain model and ItemEntity.
 * Uses MapStruct for automatic implementation generation.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {ProductEntityMapper.class})
public interface ItemEntityMapper {

    ItemEntity toEntity(Item cartItem);

    Item toDomain(ItemEntity cartItemEntity);

    List<ItemEntity> toEntityList(List<Item> cartItemList);

    List<Item> toDomainList(List<ItemEntity> cartItemEntityList);

}
