package com.jhernandez.backend.bazaar.application.port;

import com.jhernandez.backend.bazaar.domain.usecase.CreateUserUseCase;
import com.jhernandez.backend.bazaar.domain.usecase.DeleteUserUseCase;
import com.jhernandez.backend.bazaar.domain.usecase.RetrieveUserUseCase;
import com.jhernandez.backend.bazaar.domain.usecase.UpdateUserUseCase;

/*
 * UserServicePort interface that aggregates all user-related use cases.
 * It extends the individual use case interfaces for creating, retrieving,
 * updating, and deleting users.
 */
public interface UserServicePort extends
        CreateUserUseCase, RetrieveUserUseCase, UpdateUserUseCase, DeleteUserUseCase {

}
