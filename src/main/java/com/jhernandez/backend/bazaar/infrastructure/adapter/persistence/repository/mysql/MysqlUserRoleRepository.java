package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository.mysql;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jhernandez.backend.bazaar.application.port.UserRoleRepositoryPort;
import com.jhernandez.backend.bazaar.domain.model.UserRole;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.mapper.UserRoleEntityMapper;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository.JpaUserRoleRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * Repository adapter for UserRole.
 * Implements UserRoleRepositoryPort to provide persistence operations using JpaUserRoleRepository.
 * Uses UserRoleEntityMapper to convert between domain model and entity.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MysqlUserRoleRepository implements UserRoleRepositoryPort {

    private final JpaUserRoleRepository userRoleRepository;
    private final UserRoleEntityMapper userRoleEntityMapper;

    @Transactional(readOnly = true)
    @Override
    public List<UserRole> findAllUserRoles() {
        log.info("Finding all user roles");
        return userRoleRepository.findAll().stream()
                .map(userRoleEntityMapper::toDomain)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public UserRole findUserRoleById(Long id) {
        log.info("Finding user role by id: {}", id);
        return userRoleRepository.findById(id)
                .map(userRoleEntityMapper::toDomain)
                .orElse(null);
    }

}
