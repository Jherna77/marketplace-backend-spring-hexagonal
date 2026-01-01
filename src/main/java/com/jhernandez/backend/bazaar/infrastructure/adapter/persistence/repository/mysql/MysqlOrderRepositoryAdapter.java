package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository.mysql;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jhernandez.backend.bazaar.application.port.OrderRepositoryPort;
import com.jhernandez.backend.bazaar.domain.model.Order;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.OrderEntity;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.mapper.OrderEntityMapper;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository.JpaOrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * Repository adapter for Order.
 * Implements OrderRepositoryPort to provide persistence operations using JpaOrderRepository.
 * Uses OrderEntityMapper to convert between domain model and entity.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MysqlOrderRepositoryAdapter implements OrderRepositoryPort {

    private final JpaOrderRepository orderRepository;
    private final OrderEntityMapper orderEntityMapper;

    @Transactional
    @Override
    public Optional<Order> saveOrder(Order order) {
        log.info("Saving order");
        OrderEntity orderEntity = orderEntityMapper.toEntity(order);
        return Optional.of(orderEntityMapper.toDomain(
                orderRepository.save(orderEntity)));
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Order> findOrderById(Long id) {
        log.info("Finding order with ID {}", id);
        return orderRepository.findById(id).map(orderEntityMapper::toDomain);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Order> findOrdersByCustomerId(Long id) {
        log.info("Finding purchase orders for customer with ID {}", id);
        return orderRepository.findByCustomerId(id).stream()
                .map(orderEntityMapper::toDomain)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Order> findOrdersByShopId(Long id) {
        log.info("Finding sale orders for shop with ID {}", id);
        return orderRepository.findByShopId(id).stream()
                .map(orderEntityMapper::toDomain)
                .toList();
    }

}
