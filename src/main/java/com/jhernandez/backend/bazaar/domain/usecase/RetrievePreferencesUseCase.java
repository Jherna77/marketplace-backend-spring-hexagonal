package com.jhernandez.backend.bazaar.domain.usecase;

import java.util.List;

import com.jhernandez.backend.bazaar.domain.exception.ProductException;
import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.model.Category;
import com.jhernandez.backend.bazaar.domain.model.Product;

/*
 * Use case interface for retrieving user preferences.
 */
public interface RetrievePreferencesUseCase {

    List<Product> getUserFavouriteProducts(Long userId) throws UserException;

    Boolean isUserFavouriteProduct(Long userId, Long productId) throws UserException, ProductException;

    List<Category> getUserFavouriteCategories(Long userId) throws UserException;

}
