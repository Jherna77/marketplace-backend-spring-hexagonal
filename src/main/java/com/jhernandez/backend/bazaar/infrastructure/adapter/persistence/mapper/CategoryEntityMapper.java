package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.Category;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.CategoryEntity;

/*
 * Mapper interface for converting between Category domain model and CategoryEntity.
 * Uses MapStruct for automatic implementation generation.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryEntityMapper {

    CategoryEntity toEntity(Category category);

    Category toDomain(CategoryEntity categoryEntity);

    List<CategoryEntity> toEntityList(List<Category> categories);

    List<Category> toDomainList(List<CategoryEntity> categoryEntities);

}
