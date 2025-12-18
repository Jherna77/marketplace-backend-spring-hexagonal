package com.jhernandez.backend.bazaar.domain.usecase;

import java.util.List;

import com.jhernandez.backend.bazaar.domain.model.UserRole;

/*
 * Use case interface for retrieving user roles.
 */
public interface RetrieveUserRoleUseCase {
    
    List<UserRole> findAllUserRoles();

    UserRole findUserRoleById(Long id);

}
