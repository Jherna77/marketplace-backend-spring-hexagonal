package com.jhernandez.backend.bazaar.application.port;

import java.util.List;
import java.util.Optional;

import com.jhernandez.backend.bazaar.domain.model.User;

/*
 * UserRepositoryPort is an interface that defines the contract for user repository operations.
 * It provides methods to create, retrieve, update, and delete users in the system.
 */
public interface UserRepositoryPort {

    Optional<User> saveUser(User user);

    List<User> findAllUsers();

    Optional<User> findUserById(Long id);

    Optional<User> findUserByEmail(String email);

    Boolean existsByEmail(String email);

    void deleteUserById(Long id);

}
