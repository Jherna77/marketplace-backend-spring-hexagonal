package com.jhernandez.backend.bazaar.domain.usecase;

import java.util.List;

import com.jhernandez.backend.bazaar.domain.exception.CategoryException;
import com.jhernandez.backend.bazaar.domain.model.Category;

/*
 * Use case interface for retrieving categories.
 */
public interface RetrieveCategoryUseCase {

    List<Category> findAllCategories();

    List<Category> findAllEnabledCategories();

    List<Category> findRandomEnabledCategories();

    Category findCategoryById(Long id) throws CategoryException;

}
