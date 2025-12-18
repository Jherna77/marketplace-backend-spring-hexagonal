package com.jhernandez.backend.bazaar.domain.usecase;

import com.jhernandez.backend.bazaar.domain.exception.CategoryException;
import com.jhernandez.backend.bazaar.domain.exception.ImageFileException;
import com.jhernandez.backend.bazaar.domain.model.Category;
import com.jhernandez.backend.bazaar.domain.model.ImageFile;

/*
 * Use case interface for creating a category.
 */
public interface CreateCategoryUseCase {
    
        void createCategory(Category category, ImageFile categoryImage) throws CategoryException, ImageFileException;

}
