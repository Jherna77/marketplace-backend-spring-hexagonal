package com.jhernandez.backend.bazaar.domain.usecase;

import com.jhernandez.backend.bazaar.domain.exception.ProductException;
import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.model.Review;

/*
 * Use case interface for creating a review.
 */
public interface CreateReviewUseCase {

    void createReview(Review review) throws UserException, ProductException;

}
