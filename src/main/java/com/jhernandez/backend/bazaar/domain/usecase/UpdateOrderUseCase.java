package com.jhernandez.backend.bazaar.domain.usecase;

import com.jhernandez.backend.bazaar.domain.exception.OrderException;
import com.jhernandez.backend.bazaar.domain.model.Order;
import com.jhernandez.backend.bazaar.domain.model.OrderStatus;

/*
 * Use case interface for updating orders.
 */
public interface UpdateOrderUseCase {

    Order updateOrderStatus(Long id, OrderStatus status) throws OrderException;

}
