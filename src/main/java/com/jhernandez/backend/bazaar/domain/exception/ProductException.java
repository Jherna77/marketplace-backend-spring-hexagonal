package com.jhernandez.backend.bazaar.domain.exception;

import com.jhernandez.backend.bazaar.domain.model.ErrorCode;

/*
 * Exception class for product-related errors.
 */
public class ProductException extends DomainException {

    public ProductException(ErrorCode errorCode) {
        super(errorCode);
    }

}
