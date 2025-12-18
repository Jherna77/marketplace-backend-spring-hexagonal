package com.jhernandez.backend.bazaar.infrastructure.adapter.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhernandez.backend.bazaar.application.port.CartServicePort;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.ItemDto;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper.ItemDtoMapper;

import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.CART;   

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * Controller for cart-related endpoints.
 */
@RestController
@RequestMapping(CART)
@CrossOrigin(originPatterns = "*")
@RequiredArgsConstructor
@Slf4j
public class CartController {

    private final CartServicePort cartService;
    private final ItemDtoMapper mapper;

    @GetMapping
    public ResponseEntity<?> getUserCart(@PathVariable Long id) {
        log.info("Finding cart for user ID {}", id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(mapper.toDtoList(cartService.getUserCart(id)));
    }

    @PostMapping
    public ResponseEntity<?> addItemToCart(@PathVariable Long id, @RequestBody ItemDto item) {
        log.info("Adding item {} to cart for the user with ID {}", item.getProduct().getName(), id);
        cartService.addItemToCart(id, mapper.toDomain(item));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<?> removeItemFromCart(@PathVariable Long id, @PathVariable Long itemId) {
        log.info("Removing item {} to cart for the user with ID {}", itemId, id);
        return ResponseEntity.status(HttpStatus.OK)
            .body(mapper.toDtoList(cartService.removeItemFromCart(id, itemId)));
    }

    @PutMapping("/{itemId}/{quantity}")
    public ResponseEntity<?> updateItemQuantity(@PathVariable Long id, @PathVariable Long itemId,
            @PathVariable int quantity) {
        log.info("Updating item {} quantity for the user with ID {}", itemId, id);
        return ResponseEntity.status(HttpStatus.OK)
            .body(mapper.toDtoList(cartService.updateItemQuantity(id, itemId, quantity)));
    }

    @PutMapping("/clear")
    public ResponseEntity<?> clearCart(@PathVariable Long id) {
        log.info("Clearing cart for the user with ID {}", id);
        return ResponseEntity.status(HttpStatus.OK)
            .body(mapper.toDtoList(cartService.clearCart(id)));
    }

}
