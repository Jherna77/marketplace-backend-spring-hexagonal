package com.jhernandez.backend.bazaar.infrastructure.adapter.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhernandez.backend.bazaar.application.port.ReviewServicePort;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.ReviewDto;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper.ReviewDtoMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.REVIEWS;

/*
 * Controller for review-related endpoints.
 */
@RestController
@RequestMapping(REVIEWS)
@CrossOrigin(originPatterns = "*")
@RequiredArgsConstructor
@Slf4j
public class ReviewController {

    private final ReviewServicePort reviewService;
    private final ReviewDtoMapper reviewDtoMapper;

    @PostMapping
    public ResponseEntity<?> createReview(@RequestBody ReviewDto review) {
        log.info("Creating review...");
        reviewService.createReview(reviewDtoMapper.toDomain(review));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<?> findReviewById(@PathVariable Long reviewId) {
        log.info("Finding review with ID {}", reviewId);
        return ResponseEntity.ok(reviewDtoMapper.toDto(reviewService.findReviewById(reviewId)));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<?> findReviewsByProductId(@PathVariable Long productId) {
        log.info("Finding reviews by product with ID {}", productId);
        return ResponseEntity.ok(reviewService.findReviewsByProductId(productId).stream()
                .map(reviewDtoMapper::toDto)
                .toList());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> findReviewsByUserId(@PathVariable Long userId) {
        log.info("Finding reviews by user with ID {}", userId);
        return ResponseEntity.ok(reviewService.findReviewsByUserId(userId).stream()
                .map(reviewDtoMapper::toDto)
                .toList());
    }

}
