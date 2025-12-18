package com.jhernandez.backend.bazaar.domain.usecase;

import com.jhernandez.backend.bazaar.domain.exception.CategoryException;
import com.jhernandez.backend.bazaar.domain.exception.ImageFileException;
import com.jhernandez.backend.bazaar.domain.exception.ProductException;

/*
 * Use case interface for deleting a category.
 */
public interface DeleteCategoryUseCase {

    void deleteCategoryById(Long id) throws CategoryException, ImageFileException, ProductException;

}
