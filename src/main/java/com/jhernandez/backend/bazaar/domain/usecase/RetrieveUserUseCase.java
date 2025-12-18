package com.jhernandez.backend.bazaar.domain.usecase;

import java.util.List;

import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.model.User;

/*
 * Use case interface for retrieving users.
 */
public interface RetrieveUserUseCase {
    
    List<User> findAllUsers();

    User findUserById(Long id) throws UserException;

    User findUserByEmail(String email) throws UserException;

    Boolean existsByEmail(String email);

}
