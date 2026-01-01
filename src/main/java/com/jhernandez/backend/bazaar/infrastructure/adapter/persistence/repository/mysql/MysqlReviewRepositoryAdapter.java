package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository.mysql;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jhernandez.backend.bazaar.application.port.ReviewRepositoryPort;
import com.jhernandez.backend.bazaar.domain.model.Review;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.mapper.ReviewEntityMapper;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository.JpaReviewRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * Repository adapter for Review.
 * Implements ReviewRepositoryPort to provide persistence operations using JpaReviewRepository.
 * Uses ReviewEntityMapper to convert between domain model and entity.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MysqlReviewRepositoryAdapter implements ReviewRepositoryPort {

    private final JpaReviewRepository reviewRepository;
    private final ReviewEntityMapper reviewEntityMapper;

    @Transactional
    @Override
    public void saveReview(Review review) {
        log.info("Saving review...");
        reviewRepository.save(reviewEntityMapper.toEntity(review));
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Review> findReviewById(Long reviewId) {
        log.info("Finding review with ID {}", reviewId);
        return reviewRepository.findById(reviewId)
                .map(reviewEntityMapper::toDomain);
    }

    @Transactional(readOnly = true)
    @Override
    public Boolean existsByOrderId(Long orderId) {
        log.info("Checking if review exists for order with ID {}", orderId);
        return reviewRepository.existsByOrderId(orderId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Review> findReviewsByProductId(Long productId) {
        log.info("Finding reviews for product with ID {}", productId);
        return reviewRepository.findByProductId(productId).stream()
                .map(reviewEntityMapper::toDomain)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Review> findReviewsByUserId(Long userId) {
        log.info("Finding reviews for user with ID {}", userId);
        return reviewRepository.findByUserId(userId).stream()
                .map(reviewEntityMapper::toDomain)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Integer> findRatingsByProductId(Long productId) {
        log.info("Finding ratings for product with ID {}", productId);
        return reviewRepository.findRatingsByProductId(productId);
    }

}
