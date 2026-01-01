package com.jhernandez.backend.bazaar.domain.usecase;

import com.jhernandez.backend.bazaar.domain.exception.CategoryException;
import com.jhernandez.backend.bazaar.domain.exception.ImageFileException;
import com.jhernandez.backend.bazaar.domain.model.Category;
import com.jhernandez.backend.bazaar.domain.model.ImageFile;


/*
 * Use case interface for updating categories.
 */
public interface UpdateCategoryUseCase {

    void updateCategory(Category category, ImageFile imageFile) throws CategoryException, ImageFileException;

    void enableCategoryById(Long id) throws CategoryException;

    void disableCategoryById(Long id) throws CategoryException;

}
