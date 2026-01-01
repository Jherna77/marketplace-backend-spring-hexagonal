package com.jhernandez.backend.bazaar.domain.exception;

import com.jhernandez.backend.bazaar.domain.model.ErrorCode;

/*
 * Exception class for image file-related errors.
 */
public class ImageFileException extends DomainException {

    public ImageFileException(ErrorCode errorCode) {
        super(errorCode);
    }

}
