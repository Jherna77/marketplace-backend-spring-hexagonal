package com.jhernandez.backend.bazaar.application.port;

import com.jhernandez.backend.bazaar.domain.usecase.CreateProductUseCase;
import com.jhernandez.backend.bazaar.domain.usecase.DeleteProductUseCase;
import com.jhernandez.backend.bazaar.domain.usecase.RetrieveProductUseCase;
import com.jhernandez.backend.bazaar.domain.usecase.UpdateProductUseCase;

/*
 * This interface combines the use cases for product management
 * into a single service port. It extends the individual use case interfaces
 * to provide a comprehensive API for product-related operations.
 */
public interface ProductServicePort extends
        CreateProductUseCase, RetrieveProductUseCase, UpdateProductUseCase, DeleteProductUseCase {

}
