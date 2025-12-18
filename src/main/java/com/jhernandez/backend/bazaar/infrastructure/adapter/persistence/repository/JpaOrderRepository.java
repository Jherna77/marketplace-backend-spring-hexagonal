package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.OrderEntity;

/*
 * Repository interface for OrderEntity.
 * Extends JpaRepository to provide CRUD operations and custom queries.
 */
public interface JpaOrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findByCustomerId(Long id);

    List<OrderEntity> findByShopId(Long id);

}
