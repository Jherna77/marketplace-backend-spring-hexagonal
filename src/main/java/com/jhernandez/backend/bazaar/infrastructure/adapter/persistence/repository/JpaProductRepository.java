package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.ProductEntity;

/*
 * Repository interface for ProductEntity.
 * Extends JpaRepository to provide CRUD operations and custom queries.
 */
public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findByNameContainingIgnoreCaseAndEnabledTrue(String name);

    @Query("SELECT p FROM ProductEntity p WHERE p.enabled = true")
    List<ProductEntity> findAllEnabled();

    @Query("SELECT p FROM ProductEntity p JOIN p.categories c WHERE c.id = ?1")
    List<ProductEntity> findByCategoryId(Long categoryId);

    @Query("SELECT p FROM ProductEntity p JOIN p.categories c WHERE c.id = ?1 AND p.enabled = true")
    List<ProductEntity> findEnabledByCategoryId(Long categoryId);

    @Query("SELECT p FROM ProductEntity p WHERE p.enabled = true AND p.hasDiscount = true ORDER BY ((p.price - p.discountPrice) / p.price) DESC")
    List<ProductEntity> findDiscountedEnabledProducts();

    @Query("SELECT p FROM ProductEntity p WHERE p.enabled = true ORDER BY p.createdAt DESC")
    List<ProductEntity> findRecentEnabledProducts();

    @Query("SELECT p FROM ProductEntity p WHERE p.enabled = true ORDER BY p.sold DESC")
    List<ProductEntity> findTopSellingEnabledProducts();

    @Query("SELECT p FROM ProductEntity p WHERE p.enabled = true ORDER BY p.rating DESC")
    List<ProductEntity> findTopRatedEnabledProducts();

}
