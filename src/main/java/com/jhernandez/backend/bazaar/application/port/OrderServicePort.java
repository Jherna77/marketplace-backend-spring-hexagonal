package com.jhernandez.backend.bazaar.application.port;

import com.jhernandez.backend.bazaar.domain.usecase.CreateOrderUseCase;
import com.jhernandez.backend.bazaar.domain.usecase.DeleteOrderUseCase;
import com.jhernandez.backend.bazaar.domain.usecase.RetrieveOrderUseCase;
import com.jhernandez.backend.bazaar.domain.usecase.UpdateOrderUseCase;

/*
 * Port interface for order service operations.
 */
public interface OrderServicePort extends
    CreateOrderUseCase, RetrieveOrderUseCase, UpdateOrderUseCase, DeleteOrderUseCase {

}
