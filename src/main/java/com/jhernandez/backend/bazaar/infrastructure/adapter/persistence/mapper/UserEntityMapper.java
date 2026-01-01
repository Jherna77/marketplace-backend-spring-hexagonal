package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.User;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.UserEntity;

/*
 * Mapper interface for converting between User domain model and UserEntity.
 * Uses MapStruct for automatic implementation generation.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {UserRoleEntityMapper.class, 
                ProductEntityMapper.class,
                ItemEntityMapper.class,
                OrderEntityMapper.class})
public interface UserEntityMapper {

    UserEntity toEntity(User user);

    User toDomain(UserEntity userEntity);

}