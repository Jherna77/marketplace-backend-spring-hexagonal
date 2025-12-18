package com.jhernandez.backend.bazaar.application.service;

import java.util.List;

import com.jhernandez.backend.bazaar.application.port.ImageServicePort;
import com.jhernandez.backend.bazaar.application.port.ProductRepositoryPort;
import com.jhernandez.backend.bazaar.application.port.ProductServicePort;
import com.jhernandez.backend.bazaar.application.port.UserRepositoryPort;
import com.jhernandez.backend.bazaar.domain.exception.CategoryException;
import com.jhernandez.backend.bazaar.domain.exception.ImageFileException;
import com.jhernandez.backend.bazaar.domain.exception.ProductException;
import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.model.ErrorCode;
import com.jhernandez.backend.bazaar.domain.model.ImageFile;
import com.jhernandez.backend.bazaar.domain.model.Product;
import com.jhernandez.backend.bazaar.domain.model.User;

/**
 * This class implements the ProductServicePort interface, which defines the
 * contract for product-related operations.
 * It can include methods for creating, retrieving, updating, and deleting
 * products.
 * The actual implementation of these methods would depend on the specific
 * requirements of the application.
 * The ProductService class is responsible for implementing the business logic
 * related to products and returns the results to the controller.
 * It interacts with the data layer to perform CRUD operations on products and
 * handles any exceptions that may occur.
 */
public class ProductService implements ProductServicePort {

    private static final Integer MAX_PRODUCTS = 10;

    private final ProductRepositoryPort productRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;
    private final ImageServicePort imageServicePort;

    public ProductService(ProductRepositoryPort productRepositoryPort, UserRepositoryPort userRepositoryPort,
            ImageServicePort imageServicePort) {
        this.productRepositoryPort = productRepositoryPort;
        this.userRepositoryPort = userRepositoryPort;
        this.imageServicePort = imageServicePort;
    }

    @Override
    public void createProduct(Product product, List<ImageFile> productImages) throws ProductException, UserException, ImageFileException {
        if (productImages == null || productImages.isEmpty()) 
            throw new ProductException(ErrorCode.PRODUCT_IMAGE_REQUIRED);
        validateProduct(product);
        
        product.setImagesUrl(imageServicePort.saveImagesList(productImages)
                .stream()
                .map(imageFile -> imageFile.getImageUrl())
                .toList());

        User owner = userRepositoryPort.findUserById(product.getShop().getId())
                .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));            
        if (!owner.getEnabled()) {
            throw new UserException(ErrorCode.PRODUCT_OWNER_DISABLED);
        }
        
        product.setShop(owner);
        owner.addProductToShop(product);
        product.enable();
        product.setCreatedAtNow();
        
        productRepositoryPort.saveProduct(product);
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepositoryPort.findAllProducts();
    }

    @Override
    public List<Product> findAllEnabledProducts() {
        return productRepositoryPort.findAllEnabledProducts();
    }

    @Override
    public List<Product> findProductsByCategoryId(Long categoryId) throws CategoryException {
        if (categoryId == null)
            throw new CategoryException(ErrorCode.CATEGORY_ID_NOT_NULL);
        return productRepositoryPort.findProductsByCategoryId(categoryId);
    }

    @Override
    public List<Product> findEnabledProductsByCategoryId(Long categoryId) throws CategoryException {
        if (categoryId == null)
            throw new CategoryException(ErrorCode.CATEGORY_ID_NOT_NULL);
        return productRepositoryPort.findEnabledProductsByCategoryId(categoryId);
    }

    @Override
    public List<Product> findEnabledProductsByName(String name) throws ProductException {
        if (name == null || name.isEmpty())
            throw new ProductException(ErrorCode.PRODUCT_NAME_REQUIRED);
        return productRepositoryPort.findEnabledProductsByName(name);
    }

    @Override
    public List<Product> findProductsByUserId(Long userId) throws UserException {
        if (userId == null)
            throw new UserException(ErrorCode.USER_ID_NOT_NULL);
        return userRepositoryPort.findUserById(userId)
                .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND))
                .getShopProducts();
    }

    @Override
    public Product findProductById(Long id) throws ProductException {
        if (id == null)
            throw new ProductException(ErrorCode.PRODUCT_ID_NOT_NULL);
        return productRepositoryPort.findProductById(id)
                .orElseThrow(() -> new ProductException(ErrorCode.PRODUCT_NOT_FOUND));
    }

    @Override
    public List<Product> findDiscountedEnabledProducts() {
        List<Product> discountedProducts = productRepositoryPort.findDiscountedEnabledProducts();
        if (discountedProducts.size() < MAX_PRODUCTS) {
            return discountedProducts;
        }
        return discountedProducts.subList(0, MAX_PRODUCTS);
    }

    @Override
    public List<Product> findRecentEnabledProducts() {
        return productRepositoryPort.findRecentEnabledProducts().subList(0, MAX_PRODUCTS);
    }

    @Override
    public List<Product> findTopSellingEnabledProducts() {
        return productRepositoryPort.findTopSellingEnabledProducts().subList(0, MAX_PRODUCTS);
    }

    @Override
    public List<Product> findTopRatedEnabledProducts() {
        return productRepositoryPort.findTopRatedEnabledProducts().subList(0, MAX_PRODUCTS);
    }

    @Override
    public void updateProduct(Product product, List<ImageFile> productsImages) throws ProductException, ImageFileException {
        if (product.getId() == null)
            throw new ProductException(ErrorCode.PRODUCT_ID_NOT_NULL);
        if (!product.getEnabled())
            throw new ProductException(ErrorCode.PRODUCT_DISABLED);
        validateProduct(product);
        Product existingProduct = findProductById(product.getId());
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setShipping(product.getShipping());
        existingProduct.setCategories(product.getCategories());
        existingProduct.setHasDiscount(product.getHasDiscount());
        existingProduct.setDiscountPrice(product.getDiscountPrice());
        existingProduct.setStock(product.getStock());
        existingProduct.setImagesUrl(getFinalImages(
                                        productsImages,
                                        product.getImagesUrl(),
                                        existingProduct.getImagesUrl())
        );
        productRepositoryPort.saveProduct(existingProduct);
    }

    @Override
    public void enableProductById(Long id) throws ProductException, UserException {
        if (id == null)
            throw new ProductException(ErrorCode.PRODUCT_ID_NOT_NULL);

        Product existingProduct = findProductById(id);
        if (existingProduct.getEnabled())
            throw new ProductException(ErrorCode.PRODUCT_ALREADY_ENABLED);
        if (!existingProduct.getShop().getEnabled())
            throw new UserException(ErrorCode.PRODUCT_OWNER_DISABLED);

        existingProduct.enable();
        productRepositoryPort.saveProduct(existingProduct);
    }

    @Override
    public void disableProductById(Long id) throws ProductException {
        if (id == null)
            throw new ProductException(ErrorCode.PRODUCT_ID_NOT_NULL);

        Product existingProduct = findProductById(id);
        if (!existingProduct.getEnabled())
            throw new ProductException(ErrorCode.PRODUCT_ALREADY_DISABLED);
        
        existingProduct.disable();
        productRepositoryPort.saveProduct(existingProduct);
    }

    @Override
    public void deleteProductById(Long id) throws ProductException {
        throw new ProductException(ErrorCode.OPERATION_NOT_ALLOWED);
    }

    private void validateProduct(Product product) throws ProductException {
        if (product.getName() == null || product.getName().isEmpty()) 
            throw new ProductException(ErrorCode.PRODUCT_NAME_REQUIRED);
        if (product.getDescription() == null || product.getDescription().isEmpty()) 
            throw new ProductException(ErrorCode.PRODUCT_DESCRIPTION_REQUIRED);
        if (product.getPrice() == null || product.getPrice() <= 0) 
            throw new ProductException(ErrorCode.PRODUCT_INVALID_PRICE);
        if (product.getShipping() == null || product.getShipping() < 0)
            throw new ProductException(ErrorCode.PRODUCT_INVALID_SHIPPING);
        if (product.getStock() == null || product.getStock() < 0)
            throw new ProductException(ErrorCode.PRODUCT_INVALID_STOCK);  
        if (product.getCategories() == null || product.getCategories().isEmpty()) 
            throw new ProductException(ErrorCode.PRODUCT_NO_CATEGORY);
        if (product.getHasDiscount()) {
            if (product.getDiscountPrice() == null || product.getDiscountPrice() < 0) 
                throw new ProductException(ErrorCode.PRODUCT_INVALID_DISCOUNT_PRICE);
            if (product.getDiscountPrice() >= product.getPrice()) 
                throw new ProductException(ErrorCode.PRODUCT_INVALID_DISCOUNT);
        };      
    }

    private List<String> getFinalImages(List<ImageFile> productsImages, List<String> finalImages, List<String> existingImages) {
        List<String> imagesToDelete = existingImages.stream()
                                                    .filter(img -> !finalImages.contains(img))
                                                    .toList();
    
        if (imagesToDelete != null && !imagesToDelete.isEmpty())
            imageServicePort.deleteImageListByUrl(imagesToDelete);
        if (productsImages != null && !productsImages.isEmpty()) 
            finalImages.addAll(imageServicePort.saveImagesList(productsImages).stream()
                                                                              .map(ImageFile::getImageUrl)
                                                                              .toList());
        return finalImages;
    }

}