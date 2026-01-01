package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.ReviewEntity;

/*
 * Repository interface for ReviewEntity.
 * Extends JpaRepository to provide CRUD operations and custom queries.
 */
public interface JpaReviewRepository extends JpaRepository<ReviewEntity, Long> {

    Boolean existsByOrderId(Long orderId);

    @Query("SELECT r FROM ReviewEntity r WHERE r.order.item.product.id = :productId")
    List<ReviewEntity> findByProductId(@Param("productId") Long productId);

    @Query("SELECT r FROM ReviewEntity r WHERE r.order.customer.id = :userId")
    List<ReviewEntity> findByUserId(@Param("userId") Long userId);

    @Query("SELECT r.rating FROM ReviewEntity r WHERE r.order.item.product.id = :productId")
    List<Integer> findRatingsByProductId(@Param("productId") Long productId);
    
}
