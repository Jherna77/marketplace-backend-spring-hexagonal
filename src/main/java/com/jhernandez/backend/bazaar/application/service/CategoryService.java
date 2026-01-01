package com.jhernandez.backend.bazaar.application.service;

import java.util.Comparator;
import java.util.List;

import com.jhernandez.backend.bazaar.application.port.CategoryRepositoryPort;
import com.jhernandez.backend.bazaar.application.port.CategoryServicePort;
import com.jhernandez.backend.bazaar.application.port.ImageServicePort;
import com.jhernandez.backend.bazaar.application.port.ProductRepositoryPort;
import com.jhernandez.backend.bazaar.domain.exception.CategoryException;
import com.jhernandez.backend.bazaar.domain.exception.ImageFileException;
import com.jhernandez.backend.bazaar.domain.model.Category;
import com.jhernandez.backend.bazaar.domain.model.ErrorCode;
import com.jhernandez.backend.bazaar.domain.model.ImageFile;
import com.jhernandez.backend.bazaar.domain.model.Product;

/*
 * This class implements the CategoryServicePort interface, which defines the contract for category-related operations.
 * It can include methods for creating, retrieving, updating, and deleting categories.
 * The actual implementation of these methods would depend on the specific requirements of the application.
 * The CategoryService class is responsible for implementing the business logic related to categories and returns the results to the controller.
 * It interacts with the data layer to perform CRUD operations on categories and handles any exceptions that may occur.
*/
public class CategoryService implements CategoryServicePort{

    private static final Long DEFAULT_CATEGORY_ID = 1L;

    private final CategoryRepositoryPort categoryRepositoryPort;
    private final ProductRepositoryPort productRepositoryPort;
    private final ImageServicePort imageServicePort;

    public CategoryService(CategoryRepositoryPort categoryRepositoryPort, ProductRepositoryPort productRepositoryPort, ImageServicePort imageServicePort) {
        this.categoryRepositoryPort = categoryRepositoryPort;
        this.productRepositoryPort = productRepositoryPort;
        this.imageServicePort = imageServicePort;
    }

    @Override
    public void createCategory(Category category, ImageFile categoryImage) throws CategoryException, ImageFileException {
        if (categoryImage == null)
            throw new CategoryException(ErrorCode.CATEGORY_IMAGE_REQUIRED);        

        validateCategoryForCreate(category);

        // Save image in storage and update URL
        category.setImageUrl(imageServicePort.saveImage(categoryImage).getImageUrl());

        category.enable();
        categoryRepositoryPort.saveCategory(category);
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepositoryPort.findAllCategories();
    }

    @Override
    public List<Category> findAllEnabledCategories() {
        return categoryRepositoryPort.findAllEnabledCategories()
            .stream()
            .sorted(Comparator.comparing(category -> category.getName().toLowerCase()))
            .toList();
    }

    @Override
    public List<Category> findRandomEnabledCategories() {
        return categoryRepositoryPort.findRandomEnabledCategories();
    }

    @Override
    public Category findCategoryById(Long id) throws CategoryException {
        return categoryRepositoryPort.findCategoryById(id)
            .orElseThrow(() -> new CategoryException(ErrorCode.CATEGORY_NOT_FOUND));
    }

    @Override
    public void updateCategory(Category category, ImageFile categoryImage) throws CategoryException, ImageFileException {
        validateCategoryForUpdate(category);
        Category existingCategory = findCategoryById(category.getId());
        existingCategory.setName(category.getName());
        
        // Save image & update URL if image is provided 
        if (categoryImage != null) {
            imageServicePort.deleteImageByUrl(existingCategory.getImageUrl());
            existingCategory.setImageUrl(imageServicePort.saveImage(categoryImage).getImageUrl());
        }
        categoryRepositoryPort.saveCategory(existingCategory);
    }

    @Override
    public void enableCategoryById(Long id) throws CategoryException {
        if (id == null)
            throw new CategoryException(ErrorCode.CATEGORY_ID_NOT_NULL);

        Category existingCategory = findCategoryById(id);
        if (existingCategory.getEnabled())
            throw new CategoryException(ErrorCode.CATEGORY_ALREADY_ENABLED);
            
        existingCategory.enable();

        categoryRepositoryPort.saveCategory(existingCategory);
    }

    /*
    * Disables a category instead of deleting it, ensuring data integrity.
    * Products under this category are reassigned to the default category.
    */
    @Override
    public void disableCategoryById(Long id) throws CategoryException {
        if (id == null)
            throw new CategoryException(ErrorCode.CATEGORY_ID_NOT_NULL);
        if (id == DEFAULT_CATEGORY_ID)
            throw new CategoryException(ErrorCode.CATEGORY_DEFAULT_DISABLE);

        Category existingCategory = findCategoryById(id);
        if (!existingCategory.getEnabled())
            throw new CategoryException(ErrorCode.CATEGORY_ALREADY_DISABLED);
        existingCategory.disable();

        removeCategoryFromProducts(id);
        
        categoryRepositoryPort.saveCategory(existingCategory);
    }

    @Override
    public void deleteCategoryById(Long id) throws CategoryException {
        throw new CategoryException(ErrorCode.OPERATION_NOT_ALLOWED);
    }

    private void validateCategoryForCreate(Category category) throws CategoryException {
        if (category.getName() == null || category.getName().isEmpty())
            throw new CategoryException(ErrorCode.CATEGORY_NAME_REQUIRED);
        if (categoryRepositoryPort.existsByName(category.getName()))
            throw new CategoryException(ErrorCode.CATEGORY_NAME_EXISTS);
    }

    private void validateCategoryForUpdate(Category category) throws CategoryException {
        if (category.getId() == null)
            throw new CategoryException(ErrorCode.CATEGORY_ID_NOT_NULL);
        if (category.getId() == DEFAULT_CATEGORY_ID)
            throw new CategoryException(ErrorCode.CATEGORY_DEFAULT_UPDATE);
        if (!category.getEnabled())
            throw new CategoryException(ErrorCode.CATEGORY_DISABLED);
        if (category.getName() == null || category.getName().isEmpty())
            throw new CategoryException(ErrorCode.CATEGORY_NAME_REQUIRED);
        String existingCategoryName = findCategoryById(category.getId()).getName();
        String categoryToUpdateName = category.getName();
        if (!categoryToUpdateName.equals(existingCategoryName) && categoryRepositoryPort.existsByName(categoryToUpdateName))
            throw new CategoryException(ErrorCode.CATEGORY_NAME_EXISTS);
    }

    private void removeCategoryFromProducts(Long categoryId) throws CategoryException {
        List<Product> categoryProducts = productRepositoryPort.findProductsByCategoryId(categoryId);
        Category defaultCategory = findCategoryById(DEFAULT_CATEGORY_ID);
        for (Product product : categoryProducts) {
            product.removeCategory(categoryId, defaultCategory);
            productRepositoryPort.saveProduct(product);
        }
    }

}
