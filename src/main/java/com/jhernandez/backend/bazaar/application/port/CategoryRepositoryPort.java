package com.jhernandez.backend.bazaar.application.port;

import java.util.List;
import java.util.Optional;

import com.jhernandez.backend.bazaar.domain.model.Category;

/*
 * CategoryRepositoryPort is an interface that defines the contract for the
 * repository layer to manage categories in the application.
 * It provides methods for creating, retrieving, updating, and deleting categories.
 */
public interface CategoryRepositoryPort {

    void saveCategory(Category category);

    List<Category> findAllCategories();

    List<Category> findAllEnabledCategories();

    List<Category> findRandomEnabledCategories();

    Optional<Category> findCategoryById(Long id);

    Boolean existsByName(String name);

    void deleteCategoryById(Long id);

}
