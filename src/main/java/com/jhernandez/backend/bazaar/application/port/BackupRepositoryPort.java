package com.jhernandez.backend.bazaar.application.port;

import java.util.List;
import java.util.Optional;

import com.jhernandez.backend.bazaar.domain.model.Backup;

/*
 * BackupRepositoryPort interface defines the contract for backup repository operations.
 * It provides methods to save and retrieve backups.
 */
public interface BackupRepositoryPort {

    void saveBackup(Backup backup);

    List<Backup> findAllBackups();

    Optional<Backup> findBackupById(Long id);

}
