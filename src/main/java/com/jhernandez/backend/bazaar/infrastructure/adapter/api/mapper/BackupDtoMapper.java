package com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.Backup;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.BackupDto;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper.helper.DateMapper;

/*
 * Mapper interface for converting Backup domain models to BackupDto objects.
 * Uses MapStruct for automatic mapping generation.
 * Includes custom date formatting via DateMapper.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = { DateMapper.class })
public interface BackupDtoMapper {

    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "formatDate")
    BackupDto toDto(Backup backup);

    List<BackupDto> toDtoList(List<Backup> backupList);

}
