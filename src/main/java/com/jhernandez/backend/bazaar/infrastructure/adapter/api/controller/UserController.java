package com.jhernandez.backend.bazaar.infrastructure.adapter.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhernandez.backend.bazaar.application.port.UserServicePort;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.UserRequestDto;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.UserResponseDto;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper.UserDtoMapper;

import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.USERS;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * UserController is a REST controller that handles HTTP requests related to users.
 * It provides endpoints for creating, retrieving, updating, and deleting users.
 * The controller uses the UserServicePort to perform the operations and the UserDtoMapper to convert between domain models and DTOs.
 */
@RestController
@RequestMapping(USERS)
@CrossOrigin(originPatterns = "*")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserServicePort userService;
    private final UserDtoMapper userDtoMapper;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody UserRequestDto user) {
        log.info("Registering user with email {}", user.getEmail());
        userService.createUser(userDtoMapper.toDomain(user));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserResponseDto> findAllUsers() {
        log.info("Finding all users");
        return userService.findAllUsers().stream()
                .map(userDtoMapper::toResponseDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findUserById(@PathVariable Long id) {
        log.info("Finding user with ID {}", id);
        return ResponseEntity.ok(userDtoMapper.toResponseDto(userService.findUserById(id)));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> findUserByEmail(@PathVariable String email) {
        log.info("Finding user with email {}", email);
        return ResponseEntity.ok(userDtoMapper.toResponseDto(userService.findUserByEmail(email)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody UserRequestDto user, @PathVariable Long id) {
        log.info("Updating user with id {}", id);
        userService.updateUser(userDtoMapper.toDomain(user));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/enable/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> enableUser(@PathVariable Long id) {
        log.info("Enabling user with ID {}", id);
        userService.enableUserById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/disable/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> disableUser(@PathVariable Long id) {
        log.info("Disabling user with ID {}", id);
        userService.disableUserById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        log.info("Deleting user with ID {}", id);
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

}
