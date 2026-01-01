package com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.Message;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.MessageDto;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper.helper.DateMapper;

/*
 * Mapper interface for converting Message domain models to MessageDto objects.
 * Uses MapStruct for automatic mapping generation.
 * Includes custom date formatting via DateMapper.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = { DateMapper.class  })
public interface MessageDtoMapper {

    @Mapping(source = "messageDate", target = "messageDate", qualifiedByName = "formatDate")
    @Mapping(source = "recipient.id", target = "recipientId")
    MessageDto toDto(Message message);

}
