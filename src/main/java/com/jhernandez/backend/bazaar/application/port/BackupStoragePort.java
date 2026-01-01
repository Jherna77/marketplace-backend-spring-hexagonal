package com.jhernandez.backend.bazaar.application.port;

import com.jhernandez.backend.bazaar.domain.exception.BackupException;

/*
 * Port interface for backup storage operations.
 */
public interface BackupStoragePort {

    void backupDatabase(String databaseFilePath) throws BackupException;

    void backupImages(String imagesFilePath) throws BackupException;

    void restoreDatabase(String databaseFilePath) throws BackupException;
    
    void restoreImages(String imagesFilePath) throws BackupException;

}
