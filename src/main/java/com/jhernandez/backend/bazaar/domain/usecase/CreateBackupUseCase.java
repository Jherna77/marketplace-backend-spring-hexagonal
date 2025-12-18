package com.jhernandez.backend.bazaar.domain.usecase;

import com.jhernandez.backend.bazaar.domain.exception.BackupException;

/*
 * Use case interface for creating a backup.
 */
public interface CreateBackupUseCase {

    void createBackup() throws BackupException;

}
