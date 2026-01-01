package com.jhernandez.backend.bazaar.application.service;

import java.util.List;

import com.jhernandez.backend.bazaar.application.port.UserRoleRepositoryPort;
import com.jhernandez.backend.bazaar.application.port.UserRoleServicePort;
import com.jhernandez.backend.bazaar.domain.model.UserRole;

/*
 * Service class for managing user roles.
 */
public class UserRoleService implements UserRoleServicePort{

    private final UserRoleRepositoryPort userRoleRepositoryPort;

    public UserRoleService(UserRoleRepositoryPort userRoleRepositoryPort) {
        this.userRoleRepositoryPort = userRoleRepositoryPort;
    }

    @Override
    public List<UserRole> findAllUserRoles() {
        return userRoleRepositoryPort.findAllUserRoles();
    }

    @Override
    public UserRole findUserRoleById(Long id) {
        return userRoleRepositoryPort.findUserRoleById(id);
    }

}
