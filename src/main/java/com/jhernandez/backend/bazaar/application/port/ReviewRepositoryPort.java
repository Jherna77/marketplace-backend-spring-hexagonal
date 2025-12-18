package com.jhernandez.backend.bazaar.application.port;

import java.util.List;
import java.util.Optional;

import com.jhernandez.backend.bazaar.domain.model.Review;

/*
 * Port interface for review repository operations.
 */
public interface ReviewRepositoryPort {

    void saveReview(Review review);

    Optional<Review> findReviewById(Long reviewId);

    Boolean existsByOrderId(Long orderId);

    List<Review> findReviewsByProductId(Long productId);

    List<Review> findReviewsByUserId(Long userId);

    List<Integer> findRatingsByProductId(Long productId);

}
