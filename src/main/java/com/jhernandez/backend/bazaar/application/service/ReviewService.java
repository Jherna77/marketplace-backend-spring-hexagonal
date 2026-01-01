package com.jhernandez.backend.bazaar.application.service;

import java.util.List;

import com.jhernandez.backend.bazaar.application.port.OrderRepositoryPort;
import com.jhernandez.backend.bazaar.application.port.ProductRepositoryPort;
import com.jhernandez.backend.bazaar.application.port.ReviewRepositoryPort;
import com.jhernandez.backend.bazaar.application.port.ReviewServicePort;
import com.jhernandez.backend.bazaar.domain.exception.OrderException;
import com.jhernandez.backend.bazaar.domain.exception.ProductException;
import com.jhernandez.backend.bazaar.domain.exception.ReviewException;
import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.model.ErrorCode;
import com.jhernandez.backend.bazaar.domain.model.Order;
import com.jhernandez.backend.bazaar.domain.model.Product;
import com.jhernandez.backend.bazaar.domain.model.Review;

/*
 * Service class for review operations.
 */
public class ReviewService implements ReviewServicePort {

    private final ReviewRepositoryPort reviewRepository;
    private final OrderRepositoryPort orderRepository;
    private final ProductRepositoryPort productRepository;

    public ReviewService(ReviewRepositoryPort reviewRepository, OrderRepositoryPort orderRepository,
            ProductRepositoryPort productRepository) {
        this.reviewRepository = reviewRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void createReview(Review review) throws ReviewException, OrderException, ProductException {
        if (reviewRepository.existsByOrderId(review.getOrder().getId())) {
            throw new ReviewException(ErrorCode.REVIEW_ALREADY_EXISTS);
        }

        Order order = orderRepository.findOrderById(review.getOrder().getId())
                .orElseThrow(() -> new OrderException(ErrorCode.ORDER_NOT_FOUND));

        Product product = productRepository.findProductById(order.getItem().getProduct().getId())
                .orElseThrow(() -> new ProductException(ErrorCode.PRODUCT_NOT_FOUND));
        review.setReviewDateNow();
        reviewRepository.saveReview(review);
        
        product.calculateRating(reviewRepository.findRatingsByProductId(product.getId()));
        productRepository.saveProduct(product);
    }

    @Override
    public Review findReviewById(Long reviewId) throws ReviewException {
        if (reviewId == null) {
            throw new ReviewException(ErrorCode.REVIEW_ID_NOT_NULL);
        }
        return reviewRepository.findReviewById(reviewId)
                .orElseThrow(() -> new ReviewException(ErrorCode.REVIEW_NOT_FOUND));
    }


    @Override
    public List<Review> findReviewsByProductId(Long productId) throws ProductException {
        if (productId == null) {
            throw new UserException(ErrorCode.PRODUCT_ID_NOT_NULL);
        }
        return reviewRepository.findReviewsByProductId(productId);
    }

    @Override
    public List<Review> findReviewsByUserId(Long userId) throws UserException {
        if (userId == null) {
            throw new UserException(ErrorCode.USER_ID_NOT_NULL);
        }
        return reviewRepository.findReviewsByUserId(userId);
    }

}
