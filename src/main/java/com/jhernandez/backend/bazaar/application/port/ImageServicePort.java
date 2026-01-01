package com.jhernandez.backend.bazaar.application.port;

import com.jhernandez.backend.bazaar.domain.usecase.CreateImageUseCase;
import com.jhernandez.backend.bazaar.domain.usecase.DeleteImageUseCase;
import com.jhernandez.backend.bazaar.domain.usecase.RetrieveImageUseCase;

/*
 * Port interface for image service operations.
 */
public interface ImageServicePort extends
    CreateImageUseCase, RetrieveImageUseCase, DeleteImageUseCase {

}
