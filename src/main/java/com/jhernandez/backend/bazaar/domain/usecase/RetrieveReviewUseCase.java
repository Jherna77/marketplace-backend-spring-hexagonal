package com.jhernandez.backend.bazaar.domain.usecase;

import java.util.List;

import com.jhernandez.backend.bazaar.domain.exception.ProductException;
import com.jhernandez.backend.bazaar.domain.exception.ReviewException;
import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.model.Review;

/*
 * Use case interface for retrieving reviews.
 */
public interface RetrieveReviewUseCase {

    Review findReviewById(Long reviewId) throws ReviewException;

    List<Review> findReviewsByProductId(Long productId) throws ProductException;

    List<Review> findReviewsByUserId(Long userId) throws UserException;

}
