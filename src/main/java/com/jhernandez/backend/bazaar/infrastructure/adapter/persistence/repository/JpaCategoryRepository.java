package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.CategoryEntity;

/*
 * Repository interface for CategoryEntity.
 * Extends JpaRepository to provide CRUD operations and custom queries.
 */
public interface JpaCategoryRepository extends JpaRepository<CategoryEntity, Long> {

    Boolean existsByName(String name);

    @Query("SELECT c FROM CategoryEntity c WHERE c.enabled = true")
    List<CategoryEntity> findAllEnabled();

    @Query(value = "SELECT * FROM categories WHERE enabled = true ORDER BY RAND() LIMIT 3", nativeQuery = true)
    List<CategoryEntity> findRandomEnabled();

}
