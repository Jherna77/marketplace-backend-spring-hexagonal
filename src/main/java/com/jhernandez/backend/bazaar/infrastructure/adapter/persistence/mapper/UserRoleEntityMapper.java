package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.UserRole;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.UserRoleEntity;

/*
 * Mapper interface for converting between UserRole domain model and UserRoleEntity.
 * Uses MapStruct for automatic implementation generation.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserRoleEntityMapper {

    UserRoleEntity toEntity(UserRole userRole);

    UserRole toDomain(UserRoleEntity userRoleEntity);

}
