package com.jhernandez.backend.bazaar.domain.usecase;

import java.util.List;

import com.jhernandez.backend.bazaar.domain.exception.ProductException;
import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.model.Product;

/*
 * Use case interface for updating user preferences.
 */
public interface UpdatePreferencesUseCase {

    List<Product> addProductToFavourites(Long userId, Long productId) throws UserException, ProductException;

    List<Product> removeProductFromFavourites(Long userId, Long productId) throws UserException, ProductException;

}
