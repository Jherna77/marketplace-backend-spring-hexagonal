package com.jhernandez.backend.bazaar.domain.exception;

import com.jhernandez.backend.bazaar.domain.model.ErrorCode;

/*
 * Exception class for payment-related errors.
 */
public class PaymentException extends DomainException {

    public PaymentException(ErrorCode errorCode) {
        super(errorCode);
    }

}
