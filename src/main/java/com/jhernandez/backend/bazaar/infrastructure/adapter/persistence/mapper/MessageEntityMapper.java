package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.Message;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.MessageEntity;

/*
 * Mapper interface for converting between Message domain model and MessageEntity.
 * Uses MapStruct for automatic implementation generation.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {UserEntityMapper.class})
public interface MessageEntityMapper {

    MessageEntity toEntity(Message message);

    Message toDomain(MessageEntity messageEntity);

}
