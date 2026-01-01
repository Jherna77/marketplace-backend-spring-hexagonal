package com.jhernandez.backend.bazaar.domain.exception;

import com.jhernandez.backend.bazaar.domain.model.ErrorCode;

/*
 * Exception class for user-related errors.
 */
public class UserException extends DomainException {

    public UserException(ErrorCode errorCode) {
        super(errorCode);
    }

}
