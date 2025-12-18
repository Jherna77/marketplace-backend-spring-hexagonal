package com.jhernandez.backend.bazaar.application.port;

import java.util.List;
import java.util.Optional;

import com.jhernandez.backend.bazaar.domain.model.Order;

/*
 * Port interface for order repository operations.
 */
public interface OrderRepositoryPort {

    Optional<Order> saveOrder(Order order);

    Optional<Order> findOrderById(Long id);

    List<Order> findOrdersByCustomerId(Long id);

    List<Order> findOrdersByShopId(Long id);

}
