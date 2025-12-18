package com.jhernandez.backend.bazaar.infrastructure.adapter.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhernandez.backend.bazaar.application.port.PreferencesServicePort;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper.ProductDtoMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.PREFERENCES;

/*
 * Controller for preferences-related endpoints.
 */
@RestController
@RequestMapping(PREFERENCES)
@CrossOrigin(originPatterns = "*")
@RequiredArgsConstructor
@Slf4j
public class PreferencesController {

    private final PreferencesServicePort preferencesService;
    private final ProductDtoMapper mapper;

    @GetMapping("/products")
    public ResponseEntity<?> getUserFavouriteProducts(@PathVariable Long id) {
        log.info("Finding favourite products for user ID {}", id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(mapper.toDtoList(preferencesService.getUserFavouriteProducts(id)));
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<?> isUserFavouriteProduct(@PathVariable Long id, @PathVariable Long productId) {
        log.info("Checking if product ID {} is in favourites for user ID {}", productId, id);
        return ResponseEntity.status(HttpStatus.OK)
            .body(preferencesService.isUserFavouriteProduct(id, productId));
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<?> addProductToFavourites(@PathVariable Long id, @PathVariable Long productId) {
        log.info("Adding product ID {} to favourites for user ID {}", productId, id);
        return ResponseEntity.status(HttpStatus.OK)
            .body(mapper.toDtoList(preferencesService.addProductToFavourites(id, productId)));
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> removeProductFromFavourites(@PathVariable Long id, @PathVariable Long productId) {
        log.info("Removing product ID {} from favourites for user ID {}", productId, id);
        return ResponseEntity.status(HttpStatus.OK)
            .body(mapper.toDtoList(preferencesService.removeProductFromFavourites(id, productId)));
    }

    @GetMapping("/categories")
    public ResponseEntity<?> getUserFavouriteCategories(@PathVariable Long id) {
        log.info("Finding favourite categories for user ID {}", id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(preferencesService.getUserFavouriteCategories(id));
    }

}
