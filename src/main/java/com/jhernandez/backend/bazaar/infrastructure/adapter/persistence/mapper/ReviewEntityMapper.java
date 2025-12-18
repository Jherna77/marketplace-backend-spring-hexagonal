package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.mapper;

import org.mapstruct.Mapper;
// import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.Review;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.ReviewEntity;

/*
 * Mapper interface for converting between Review domain model and ReviewEntity.
 * Uses MapStruct for automatic implementation generation.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = { OrderEntityMapper.class })
public interface ReviewEntityMapper {

    ReviewEntity toEntity (Review review);

    Review toDomain(ReviewEntity reviewEntity);

}
