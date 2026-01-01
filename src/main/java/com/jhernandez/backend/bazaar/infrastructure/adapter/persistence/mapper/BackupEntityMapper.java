package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.jhernandez.backend.bazaar.domain.model.Backup;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.BackupEntity;


/*
 * Mapper interface for converting between Backup domain model and BackupEntity.
 * Uses MapStruct for automatic implementation generation.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BackupEntityMapper {

    Backup toDomain(BackupEntity backupEntity);

    BackupEntity toEntity(Backup backup);

    List<Backup> toDomainList(List<BackupEntity> backupEntityList);

}
