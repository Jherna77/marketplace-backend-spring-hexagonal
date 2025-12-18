package com.jhernandez.backend.bazaar.application.port;

import com.jhernandez.backend.bazaar.domain.usecase.RetrieveCartUseCase;
import com.jhernandez.backend.bazaar.domain.usecase.UpdateCartUseCase;

/*
 * This interface combines the functionalities of both UpdateCartUseCase and RetrieveCartUseCase
 * No additional methods are needed here as it inherits from both interfaces
 * This design promotes separation of concerns and adheres to the Interface Segregation Principle
 */
public interface CartServicePort extends RetrieveCartUseCase, UpdateCartUseCase {

}
