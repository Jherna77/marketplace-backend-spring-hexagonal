package com.jhernandez.backend.bazaar.domain.usecase;

import java.util.List;

import com.jhernandez.backend.bazaar.domain.exception.ImageFileException;
import com.jhernandez.backend.bazaar.domain.exception.ProductException;
import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.model.ImageFile;
import com.jhernandez.backend.bazaar.domain.model.Product;

/*
 * Use case interface for creating a product.
 */
public interface CreateProductUseCase {

    void createProduct(Product product, List<ImageFile> productImages) throws ProductException, UserException, ImageFileException;

}
