package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository.mysql;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jhernandez.backend.bazaar.application.port.BackupRepositoryPort;
import com.jhernandez.backend.bazaar.domain.model.Backup;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.mapper.BackupEntityMapper;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository.JpaBackupRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * Repository adapter for Backup.
 * Implements BackupRepositoryPort to provide persistence operations using JpaBackupRepository.
 * Uses BackupEntityMapper to convert between domain model and entity.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MysqlBackupRepositoryAdapter implements BackupRepositoryPort {

    private final JpaBackupRepository backupRepository;
    private final BackupEntityMapper backupEntityMapper;

    @Transactional
    @Override
    public void saveBackup(Backup backup) {
        log.info("Saving backup with database file: {} and images file: {}", 
                 backup.getDataBaseFileName(), backup.getImagesFileName());
        backupRepository.save(backupEntityMapper.toEntity(backup));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Backup> findAllBackups() {
        log.info("Finding all backups");
        return backupEntityMapper.toDomainList(backupRepository.findAll());
    }

    @Override
    public Optional<Backup> findBackupById(Long id) {
        log.info("Finding backup with ID: {}", id);
        return backupRepository.findById(id).map(backupEntityMapper::toDomain);
    }

}
