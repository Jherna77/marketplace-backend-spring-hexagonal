package com.jhernandez.backend.bazaar.domain.usecase;

import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.model.User;

/*
 * Use case interface for updating users.
 */
public interface UpdateUserUseCase {
    
    void updateUser(User user) throws UserException;

    void enableUserById(Long id) throws UserException;

    void disableUserById(Long id) throws UserException;

}
