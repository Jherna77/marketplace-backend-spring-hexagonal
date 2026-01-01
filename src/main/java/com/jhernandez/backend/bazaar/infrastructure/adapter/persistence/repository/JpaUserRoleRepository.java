package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.UserRoleEntity;

/*
 * Repository interface for UserRoleEntity.
 * Extends JpaRepository to provide CRUD operations and custom queries.
 */
public interface JpaUserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
    Optional<UserRoleEntity> findByName(String name);

}
