package com.jhernandez.backend.bazaar.application.port;

import com.jhernandez.backend.bazaar.domain.usecase.CreateBackupUseCase;
import com.jhernandez.backend.bazaar.domain.usecase.RetrieveBackupUseCase;

/*
 * BackupServicePort interface defines the contract for backup service operations.
 * It extends the CreateBackupUseCase and RetrieveBackupUseCase interfaces.
 */
public interface BackupServicePort extends CreateBackupUseCase, RetrieveBackupUseCase{

}
