package com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.User;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.UserRequestDto;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.UserResponseDto;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper.helper.NameDisabler;

/*
 * Mapper interface for converting User domain models to UserResponseDto objects and vice versa.
 * Uses MapStruct for automatic mapping generation.
 * Includes custom name adjustment via NameDisabler.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = { UserRoleDtoMapper.class, NameDisabler.class })
public interface UserDtoMapper {

    @Mapping(target = "name", source = ".", qualifiedByName = "adjustUserName")    
    UserResponseDto toResponseDto(User user);

    @Mapping(target = "shopProducts", ignore = true)
    @Mapping(target = "cart", ignore = true)
    @Mapping(target = "favProducts", ignore = true)
    User toDomain(UserRequestDto userRequestDto);

}
