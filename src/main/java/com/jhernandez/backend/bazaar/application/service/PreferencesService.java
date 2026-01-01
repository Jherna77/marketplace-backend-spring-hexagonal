package com.jhernandez.backend.bazaar.application.service;

import java.util.List;

import com.jhernandez.backend.bazaar.application.port.PreferencesServicePort;
import com.jhernandez.backend.bazaar.application.port.ProductRepositoryPort;
import com.jhernandez.backend.bazaar.application.port.UserRepositoryPort;
import com.jhernandez.backend.bazaar.domain.exception.ProductException;
import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.model.Category;
import com.jhernandez.backend.bazaar.domain.model.ErrorCode;
import com.jhernandez.backend.bazaar.domain.model.Product;
import com.jhernandez.backend.bazaar.domain.model.User;

/*
 * Service class for user preferences operations.
 */
public class PreferencesService implements PreferencesServicePort {

    private final UserRepositoryPort userRepository;
    private final ProductRepositoryPort productRepository;

    public PreferencesService(UserRepositoryPort userRepository, ProductRepositoryPort productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getUserFavouriteProducts(Long userId) throws UserException {
        return userRepository.findUserById(userId)
                .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND))
                .getFavProducts();
    }

    @Override
    public Boolean isUserFavouriteProduct(Long userId, Long productId) throws UserException, ProductException {
        User user = userRepository.findUserById(userId)
                .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));
        Product product = productRepository.findProductById(productId)
                .orElseThrow(() -> new ProductException(ErrorCode.PRODUCT_NOT_FOUND));
        return user.getFavProducts().stream()
                .anyMatch(favourite -> favourite.getId().equals(product.getId()));
    }

    @Override
    public List<Product> addProductToFavourites(Long userId, Long productId) throws UserException, ProductException {
        User user = userRepository.findUserById(userId)
                .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));
        Product product = productRepository.findProductById(productId)
                .orElseThrow(() -> new ProductException(ErrorCode.PRODUCT_NOT_FOUND));
        user.addProductToFavourites(product);
        user.addCategoriesToFavourites(product.getCategories());

        return userRepository.saveUser(user)
                .orElseThrow(() -> new UserException(ErrorCode.USER_SAVE_ERROR))
                .getFavProducts();
    }

    @Override
    public List<Product> removeProductFromFavourites(Long userId, Long productId) throws UserException, ProductException {
        User user = userRepository.findUserById(userId)
                .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));
        user.removeProductFromFavourites(productId);
        return userRepository.saveUser(user)
                .orElseThrow(() -> new UserException(ErrorCode.USER_SAVE_ERROR))
                .getFavProducts();
    }

    @Override
    public List<Category> getUserFavouriteCategories(Long userId) throws UserException {
        return userRepository.findUserById(userId)
                .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND))
                .getFavCategories();
    }

}
