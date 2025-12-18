package com.jhernandez.backend.bazaar.infrastructure.adapter.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.ARG_PRODUCT;
import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.ARG_IMAGE;
import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.PRODUCTS;
import com.jhernandez.backend.bazaar.application.port.ProductServicePort;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.ProductDto;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper.ImageFileDtoMapper;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper.ProductDtoMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/*
 * Controller for product-related endpoints.
 * Handles CRUD operations and product retrieval.
 * Supports image uploads for products.
 * Access control is enforced for certain operations.
 * Uses DTOs and mappers for data transformation.
 * Logs key actions for monitoring and debugging.
 * Follows RESTful API design principles.
 * Integrates with the ProductServicePort for business logic.
 */
@RestController
@RequestMapping(PRODUCTS)
@CrossOrigin(originPatterns = "*")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductServicePort productService;
    private final ProductDtoMapper productDtoMapper;
    private final ImageFileDtoMapper imageFileDtoMapper;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SHOP')")
    public ResponseEntity<?> createProduct(
        @RequestPart(ARG_PRODUCT) ProductDto product,
        @RequestPart(ARG_IMAGE) List<MultipartFile> imageFileList) {
        log.info("Creating product: {}", product.getName());
        productService.createProduct(
                productDtoMapper.toDomain(product),
                imageFileDtoMapper.toDomainList(imageFileList));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<ProductDto> findAllPoducts() {
        log.info("Finding all products");
        return productService.findAllProducts().stream()
            .map(productDtoMapper::toDto)
            .toList();
    }

    @GetMapping("/enabled")
    public List<ProductDto> findAllEnabledPoducts() {
        log.info("Finding all enabled products");
        return productService.findAllEnabledProducts().stream()
            .map(productDtoMapper::toDto)
            .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findProductById(@PathVariable Long id) {
        log.info("Finding product with ID {}", id);
        return ResponseEntity.ok(productDtoMapper.toDto(productService.findProductById(id)));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> findProductsByUserId(@PathVariable Long userId) {
        log.info("Finding products by shop with ID {}", userId);
        return ResponseEntity.ok(productService.findProductsByUserId(userId).stream()
                .map(productDtoMapper::toDto)
                .toList());
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> findProductsByCategoryId(@PathVariable Long categoryId) {
        log.info("Finding products by category with ID {}", categoryId);
        return ResponseEntity.ok(productService.findEnabledProductsByCategoryId(categoryId).stream()
                .map(productDtoMapper::toDto)
                .toList());
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<?> findProductsByName(@PathVariable String name) {
        log.info("Finding products by name {}", name);
        return ResponseEntity.ok(productService.findEnabledProductsByName(name).stream()
                .map(productDtoMapper::toDto)
                .toList());
    }

    @GetMapping("/discounted")
    public ResponseEntity<?> findDiscountedProducts() {
        log.info("Finding discounted products");
        return ResponseEntity.ok(productService.findDiscountedEnabledProducts().stream()
                .map(productDtoMapper::toDto)
                .toList());
    }

    @GetMapping("/recent")
    public ResponseEntity<?> findRecentProducts() {
        log.info("Finding recent products");
        return ResponseEntity.ok(productService.findRecentEnabledProducts().stream()
                .map(productDtoMapper::toDto)
                .toList());
    }

    @GetMapping("/top-selling")
    public ResponseEntity<?> findTopSellingProducts() {
        log.info("Finding top selling products");
        return ResponseEntity.ok(productService.findTopSellingEnabledProducts().stream()
                .map(productDtoMapper::toDto)
                .toList());
    }

    @GetMapping("/top-rated")
    public ResponseEntity<?> findTopRatedProducts() {
        log.info("Finding top rated products");
        return ResponseEntity.ok(productService.findTopRatedEnabledProducts().stream()
                .map(productDtoMapper::toDto)
                .toList());
    }
    
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SHOP')")
    public ResponseEntity<?> updateProduct(
        @RequestPart(ARG_PRODUCT) ProductDto product,
        @RequestPart(value = ARG_IMAGE, required = false) List<MultipartFile> imageFileList,
        @PathVariable Long id) {
        log.info("Updating product with ID {}", id);
        productService.updateProduct(
                productDtoMapper.toDomain(product),
                imageFileDtoMapper.toDomainList(imageFileList));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/enable/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SHOP')")
    public ResponseEntity<?> enableProduct(@PathVariable Long id) {
        log.info("Enabling product with ID {}", id);
        productService.enableProductById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/disable/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SHOP')")
    public ResponseEntity<?> disableProduct(@PathVariable Long id) {
        log.info("Disabling product with ID {}", id);
        productService.disableProductById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SHOP')")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        log.info("Deleting product with ID {}", id);
        productService.deleteProductById(id);
        return ResponseEntity.ok().build();
        }

}
