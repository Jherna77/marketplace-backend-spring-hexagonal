package com.jhernandez.backend.bazaar.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.*;

import com.jhernandez.backend.bazaar.application.port.ImageServicePort;
import com.jhernandez.backend.bazaar.application.port.ProductRepositoryPort;
import com.jhernandez.backend.bazaar.application.port.UserRepositoryPort;
import com.jhernandez.backend.bazaar.domain.exception.ProductException;
import com.jhernandez.backend.bazaar.domain.model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/*
 * Unit tests for ProductService class.
 * Covers scenarios for creating, finding, enabling/disabling, and updating products.
 * Uses Mockito for mocking dependencies and JUnit 5 for assertions.
 * Ensures proper exception handling and verifies interactions with repository ports.
 */
public class ProductServiceTest {

    @Mock
    private ProductRepositoryPort productRepositoryPort;

    @Mock
    private UserRepositoryPort userRepositoryPort;

    @Mock
    private ImageServicePort imageServicePort;

    @InjectMocks
    private ProductService productService;

    private Product product;
    private User user;
    private List<ImageFile> imageFiles;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setId(1L);
        user.setEnabled(true);
        user.setShopProducts(new ArrayList<>());

        product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setPrice(100.0);
        product.setShipping(10.0);
        product.setStock(50);
        product.setCategories(List.of(new Category(3L, "Category1", "image1.png", true)));
        product.setShop(user);
        product.setHasDiscount(false);
        product.setImagesUrl(List.of("image1.png"));

        ImageFile image = new ImageFile(null, "image1.png", "image/png", "url1");
        imageFiles = List.of(image);
    }

    @Test
    void testCreateProduct_success() throws Exception {
        when(userRepositoryPort.findUserById(1L)).thenReturn(Optional.of(user));
        when(imageServicePort.saveImagesList(imageFiles))
                .thenReturn(List.of(new ImageFile(null, "img.png", "image/png", "url.png")));

        productService.createProduct(product, imageFiles);

        verify(productRepositoryPort, times(1)).saveProduct(any(Product.class));
    }

    @Test
    void testCreateProduct_missingImage_throwsException() {
        Exception exception = assertThrows(ProductException.class, () -> {
            productService.createProduct(product, new ArrayList<>());
        });
        assertEquals(ErrorCode.PRODUCT_IMAGE_REQUIRED, ((ProductException) exception).getErrorCode());
    }

    @Test
    void testFindProductById_success() throws ProductException {
        when(productRepositoryPort.findProductById(1L)).thenReturn(Optional.of(product));
        Product result = productService.findProductById(1L);
        assertEquals(product.getName(), result.getName());
    }

    @Test
    void testEnableProductById_success() throws Exception {
        product.setEnabled(false);
        product.setShop(user);
        when(productRepositoryPort.findProductById(1L)).thenReturn(Optional.of(product));

        productService.enableProductById(1L);

        verify(productRepositoryPort).saveProduct(product);
        assertTrue(product.getEnabled());
    }

    @Test
    void testDisableProductById_success() throws Exception {
        product.setEnabled(true);
        when(productRepositoryPort.findProductById(1L)).thenReturn(Optional.of(product));

        productService.disableProductById(1L);

        verify(productRepositoryPort).saveProduct(product);
        assertFalse(product.getEnabled());
    }

    @Test
    void testUpdateProduct_success() throws Exception {
        product.setEnabled(true);
        product.setId(1L);
        when(productRepositoryPort.findProductById(1L)).thenReturn(Optional.of(product));

        productService.updateProduct(product, null);

        verify(productRepositoryPort).saveProduct(any(Product.class));
    }
}
