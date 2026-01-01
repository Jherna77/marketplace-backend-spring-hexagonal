package com.jhernandez.backend.bazaar.application.port;

import java.util.List;

import com.jhernandez.backend.bazaar.domain.model.UserRole;

/*
 * UserRoleRepositoryPort is an interface that defines the contract for user role repository operations.
 * It provides methods to retrieve user roles in the system.
 */
public interface UserRoleRepositoryPort {

    List<UserRole> findAllUserRoles();

    UserRole findUserRoleById(Long id);

}
