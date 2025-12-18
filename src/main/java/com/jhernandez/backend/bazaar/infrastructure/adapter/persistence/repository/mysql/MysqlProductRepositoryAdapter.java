package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository.mysql;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jhernandez.backend.bazaar.application.port.ProductRepositoryPort;
import com.jhernandez.backend.bazaar.domain.model.Product;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.mapper.ProductEntityMapper;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository.JpaProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * Repository adapter for Product.
 * Implements ProductRepositoryPort to provide persistence operations using JpaProductRepository.
 * Uses ProductEntityMapper to convert between domain model and entity.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MysqlProductRepositoryAdapter implements ProductRepositoryPort {

    private final JpaProductRepository productRepository;
    private final ProductEntityMapper productEntityMapper;
    
    @Transactional
    @Override
    public void saveProduct(Product product) {
        log.info("Saving product {}", product.getName());
        productRepository.save(productEntityMapper.toEntity(product));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAllProducts() {
        log.info("Finding all products {}");
        return productRepository.findAll().stream()
                .map(productEntityMapper::toDomain)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAllEnabledProducts() {
        log.info("Finding all enabled products {}");
        return productRepository.findAllEnabled().stream()
                .map(productEntityMapper::toDomain)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> findProductsByCategoryId(Long categoryId) {
        log.info("Finding all products by category with ID {}", categoryId);
        return productRepository.findByCategoryId(categoryId).stream()
                .map(productEntityMapper::toDomain)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> findEnabledProductsByCategoryId(Long categoryId) {
        log.info("Finding all products by category with ID {}", categoryId);
        return productRepository.findEnabledByCategoryId(categoryId).stream()
                .map(productEntityMapper::toDomain)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> findEnabledProductsByName(String name) {
        log.info("Finding all products by name {}", name);
        return productRepository.findByNameContainingIgnoreCaseAndEnabledTrue(name).stream()
                .map(productEntityMapper::toDomain)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> findDiscountedEnabledProducts() {
        log.info("Finding discounted enabled products");
        return productRepository.findDiscountedEnabledProducts().stream()
                .map(productEntityMapper::toDomain)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> findRecentEnabledProducts() {
        log.info("Finding recent enabled products");
        return productRepository.findRecentEnabledProducts().stream()
                .map(productEntityMapper::toDomain)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> findTopSellingEnabledProducts() {
        log.info("Finding top selling enabled products");
        return productRepository.findTopSellingEnabledProducts().stream()
                .map(productEntityMapper::toDomain)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Product> findTopRatedEnabledProducts() {
        log.info("Finding top rated enabled products");
        return productRepository.findTopRatedEnabledProducts().stream()
                .map(productEntityMapper::toDomain)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Product> findProductById(Long id) {
        log.info("Finding product with ID {}", id);
        return productRepository.findById(id).map(productEntityMapper::toDomain);
    }

    @Transactional
    @Override
    public void deleteProductById(Long id) {
        log.info("Deleting product with ID {}", id);
        productRepository.deleteById(id);
    }

}
