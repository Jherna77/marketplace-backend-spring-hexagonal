package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.BackupEntity;


/*
 * Repository interface for BackupEntity.
 * Extends JpaRepository to provide CRUD operations.
 */
public interface JpaBackupRepository extends JpaRepository<BackupEntity, Long> {

}
