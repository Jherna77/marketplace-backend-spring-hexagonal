package com.jhernandez.backend.bazaar.domain.usecase;

import java.util.List;

import com.jhernandez.backend.bazaar.domain.exception.ProductException;
import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.model.Item;

/*
 * Use case interface for updating the shopping cart.
 */
public interface UpdateCartUseCase {

    void addItemToCart(Long userId, Item item) throws UserException, ProductException;

    List<Item> removeItemFromCart(Long userId, Long itemId) throws UserException;

    List<Item> updateItemQuantity(Long userId, Long itemId, int quantity) throws UserException, ProductException;

    List<Item> clearCart(Long userId) throws UserException;;

}
