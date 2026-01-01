package com.jhernandez.backend.bazaar.domain.usecase;

import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.model.User;

/*
 * Use case interface for creating a user.
 */
public interface CreateUserUseCase {

    void createUser(User user) throws UserException;

}
