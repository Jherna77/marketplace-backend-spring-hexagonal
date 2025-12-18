package com.jhernandez.backend.bazaar.domain.usecase;

import java.util.List;

import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.model.Item;

/*
 * Use case interface for retrieving a user's cart.
 */
public interface RetrieveCartUseCase {

    List<Item> getUserCart(Long userId) throws UserException;

}
