package com.jhernandez.backend.bazaar.infrastructure.adapter.api.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhernandez.backend.bazaar.application.port.UserRoleServicePort;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.UserRoleDto;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper.UserRoleDtoMapper;

import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.ROLES;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(ROLES)
@CrossOrigin(originPatterns = "*")
@RequiredArgsConstructor
@Slf4j
public class UserRoleController {

    private final UserRoleServicePort userRoleService;
    private final UserRoleDtoMapper userRoleDtoMapper;

    @GetMapping
    public List<UserRoleDto> findAllUserRoles() {
        log.info("Finding all user roles");
        return userRoleService.findAllUserRoles().stream()
                .map(userRoleDtoMapper::toDto)
                .toList();
    }


}
