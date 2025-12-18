package com.jhernandez.backend.bazaar.domain.usecase;

import com.jhernandez.backend.bazaar.domain.exception.OrderException;
import com.jhernandez.backend.bazaar.domain.exception.UserException;

/*
 * Use case interface for creating an order.
 */
public interface CreateOrderUseCase {

    void createOrderFromCart(Long userId) throws OrderException, UserException;

}
