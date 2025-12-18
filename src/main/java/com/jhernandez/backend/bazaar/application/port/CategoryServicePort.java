package com.jhernandez.backend.bazaar.application.port;

import com.jhernandez.backend.bazaar.domain.usecase.CreateCategoryUseCase;
import com.jhernandez.backend.bazaar.domain.usecase.DeleteCategoryUseCase;
import com.jhernandez.backend.bazaar.domain.usecase.RetrieveCategoryUseCase;
import com.jhernandez.backend.bazaar.domain.usecase.UpdateCategoryUseCase;

/*
 * CategoryServicePort is an interface that aggregates all the use cases related to
 * category management. It extends the individual use case interfaces for creating,
 * retrieving, updating, and deleting categories.
 */
public interface CategoryServicePort extends
        CreateCategoryUseCase, RetrieveCategoryUseCase, UpdateCategoryUseCase, DeleteCategoryUseCase {

}
