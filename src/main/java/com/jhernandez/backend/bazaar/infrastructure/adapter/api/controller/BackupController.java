package com.jhernandez.backend.bazaar.infrastructure.adapter.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.jhernandez.backend.bazaar.application.port.BackupServicePort;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper.BackupDtoMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.BACKUPS;

/*
 * Controller for backup-related endpoints.
 */
@RestController
@RequestMapping(BACKUPS)
@CrossOrigin(originPatterns = "*")
@RequiredArgsConstructor
@Slf4j
public class BackupController {

    private final BackupServicePort backupService;
    private final BackupDtoMapper backupDtoMapper;

    /**
     * Endpoint to create a backup of the application data.
     * Only accessible by users with the ADMIN role.
     *
     * @return ResponseEntity indicating the status of the backup creation.
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
        public ResponseEntity<?> createBackup() {
        log.info("Creating backup...");
        backupService.createBackup();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> findAllBackups() {
        log.info("Finding all backups");
        return ResponseEntity.ok(backupDtoMapper.toDtoList(backupService.findAllBackups()));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> findBackupById(@PathVariable Long id) {
        log.info("Finding backup by id: {}", id);
        return ResponseEntity.ok(backupDtoMapper.toDto(backupService.findBackupById(id)));
    }

    @PutMapping("/restore/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> restoreBackup(@PathVariable Long id) {
        log.info("Restoring backup with id: {}", id);
        backupService.restoreBackup(id);
        return ResponseEntity.ok().build();
    }
}
