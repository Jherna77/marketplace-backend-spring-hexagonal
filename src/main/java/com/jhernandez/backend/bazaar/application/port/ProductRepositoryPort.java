package com.jhernandez.backend.bazaar.application.port;

import java.util.List;
import java.util.Optional;

import com.jhernandez.backend.bazaar.domain.model.Product;

/*
 * ProductRepositoryPort interface defines the contract for product repository operations.
 * It provides methods to create, retrieve, update, and delete products.
 */
public interface ProductRepositoryPort {

    void saveProduct(Product product);

    List<Product> findAllProducts();

    List<Product> findAllEnabledProducts();

    List<Product> findProductsByCategoryId(Long categoryId);
    
    List<Product> findEnabledProductsByCategoryId(Long categoryId);

    List<Product> findEnabledProductsByName(String name);

    List<Product> findDiscountedEnabledProducts();

    List<Product> findRecentEnabledProducts();

    List<Product> findTopSellingEnabledProducts();

    List<Product> findTopRatedEnabledProducts();

    Optional<Product> findProductById(Long id);
  
    void deleteProductById(Long id);

}
