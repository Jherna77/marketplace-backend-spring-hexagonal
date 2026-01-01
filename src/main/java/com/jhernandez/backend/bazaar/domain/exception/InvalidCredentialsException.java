package com.jhernandez.backend.bazaar.domain.exception;

import com.jhernandez.backend.bazaar.domain.model.ErrorCode;

public class InvalidCredentialsException extends DomainException {

    public InvalidCredentialsException(ErrorCode errorCode) {
        super(errorCode);
    }

}
