package com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.Item;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.ItemDto;

/*
 * Mapper interface for converting Item domain models to ItemDto objects.
 * Uses MapStruct for automatic mapping generation.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = { ProductDtoMapper.class })
public interface ItemDtoMapper {

    ItemDto toDto(Item item);

    Item toDomain(ItemDto itemdto);

    List<ItemDto> toDtoList(List<Item> itemList);

    List<Item> toDomainList(List<ItemDto> itemDtoList);

}
