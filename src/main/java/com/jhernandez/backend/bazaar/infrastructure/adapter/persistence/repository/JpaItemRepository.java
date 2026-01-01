package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.ItemEntity;

/*
 * Repository interface for ItemEntity.
 * Extends JpaRepository to provide CRUD operations.
 */
public interface JpaItemRepository extends JpaRepository<ItemEntity, Long> {
 
}
