package com.jhernandez.backend.bazaar.domain.exception;

import com.jhernandez.backend.bazaar.domain.model.ErrorCode;

/*
 * Exception class for review-related errors.
 */
public class ReviewException extends DomainException {

    public ReviewException(ErrorCode errorCode) {
        super(errorCode);
    }

}
