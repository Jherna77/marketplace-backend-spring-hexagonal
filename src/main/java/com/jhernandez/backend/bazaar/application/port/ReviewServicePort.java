package com.jhernandez.backend.bazaar.application.port;

import com.jhernandez.backend.bazaar.domain.usecase.CreateReviewUseCase;
import com.jhernandez.backend.bazaar.domain.usecase.RetrieveReviewUseCase;

/*
 * ReviewServicePort interface defines the contract for review service operations.
 * It extends the CreateReviewUseCase and RetrieveReviewUseCase interfaces.
 */
public interface ReviewServicePort extends CreateReviewUseCase, RetrieveReviewUseCase{

}
