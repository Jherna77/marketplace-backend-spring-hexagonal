package com.jhernandez.backend.bazaar.domain.usecase;

import com.jhernandez.backend.bazaar.domain.exception.ImageFileException;
import com.jhernandez.backend.bazaar.domain.exception.UserException;

/*
 * Use case interface for deleting a user.
 */
public interface DeleteUserUseCase {

    void deleteUserById(Long id) throws UserException, ImageFileException;

}
