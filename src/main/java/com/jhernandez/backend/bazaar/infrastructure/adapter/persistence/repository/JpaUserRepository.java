package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.UserEntity;

/*
 * Repository interface for UserEntity.
 * Extends JpaRepository to provide CRUD operations and custom queries.
 */
public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {

    Boolean existsByEmail(String email);

    Optional<UserEntity> findByEmail(String email);

}
