package com.jhernandez.backend.bazaar.application.service;

import com.jhernandez.backend.bazaar.application.port.UserRepositoryPort;
import com.jhernandez.backend.bazaar.application.port.UserRoleRepositoryPort;
import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.model.ErrorCode;
import com.jhernandez.backend.bazaar.domain.model.User;
import com.jhernandez.backend.bazaar.domain.model.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/*
 * Unit tests for UserService class.
 * Covers scenarios for creating, finding, and updating users.
 * Uses Mockito for mocking dependencies and JUnit 5 for assertions.
 * Ensures proper exception handling and verifies interactions with repository ports.
 */
class UserServiceTest {

    private UserRepositoryPort userRepositoryPort;
    private PasswordEncoder passwordEncoder;
    private UserRoleRepositoryPort userRoleRepositoryPort;

    private UserService userService;

    private User validUser;

    @BeforeEach
    void setUp() {
        userRepositoryPort = mock(UserRepositoryPort.class);
        passwordEncoder = mock(PasswordEncoder.class);
        userRoleRepositoryPort = mock(UserRoleRepositoryPort.class);

        userService = new UserService(userRepositoryPort, passwordEncoder, userRoleRepositoryPort);

        UserRole role = new UserRole(2L, "ROLE_CUSTOMER");
        validUser = new User();
        validUser.setId(2L);
        validUser.setEmail("test@example.com");
        validUser.setPassword("Password1@");
        validUser.setName("Test");
        validUser.setSurnames("User");
        validUser.setAddress("Street 123");
        validUser.setCity("City");
        validUser.setProvince("Province");
        validUser.setZipCode("12345");
        validUser.setRole(role);
        validUser.setEnabled(true);

        when(userRoleRepositoryPort.findUserRoleById(2L)).thenReturn(role);
        when(passwordEncoder.encode(anyString())).thenReturn("encoded-password");
    }

    @Test
    void testCreateUser_success() throws UserException {
        when(userRepositoryPort.existsByEmail(validUser.getEmail())).thenReturn(false);

        userService.createUser(validUser);

        verify(userRepositoryPort, times(1)).saveUser(any(User.class));
    }

    @Test
    void testCreateUser_existingEmail_throwsException() {
        when(userRepositoryPort.existsByEmail(validUser.getEmail())).thenReturn(true);

        UserException exception = assertThrows(UserException.class, () -> {
            userService.createUser(validUser);
        });

        assertEquals(ErrorCode.USER_EMAIL_EXISTS, exception.getErrorCode());
    }

    @Test
    void testFindUserById_success() throws UserException {
        when(userRepositoryPort.findUserById(2L)).thenReturn(Optional.of(validUser));

        User found = userService.findUserById(2L);

        assertEquals(validUser.getEmail(), found.getEmail());
    }

    @Test
    void testFindUserById_notFound() {
        when(userRepositoryPort.findUserById(2L)).thenReturn(Optional.empty());

        UserException exception = assertThrows(UserException.class, () -> {
            userService.findUserById(2L);
        });

        assertEquals(ErrorCode.USER_NOT_FOUND, exception.getErrorCode());
    }

    @Test
    void testUpdateUser_success() throws UserException {
        User updatedUser = new User();
        updatedUser.setId(2L);
        updatedUser.setEnabled(true);
        updatedUser.setRole(validUser.getRole());
        updatedUser.setName("Updated");
        updatedUser.setSurnames("Surname");
        updatedUser.setAddress("Address");
        updatedUser.setCity("City");
        updatedUser.setProvince("Province");
        updatedUser.setZipCode("54321");

        when(userRepositoryPort.findUserById(2L)).thenReturn(Optional.of(validUser));

        userService.updateUser(updatedUser);

        verify(userRepositoryPort).saveUser(any(User.class));
    }

    @Test
    void testEnableUserById_success() throws UserException {
        validUser.disable();
        when(userRepositoryPort.findUserById(2L)).thenReturn(Optional.of(validUser));

        userService.enableUserById(2L);

        verify(userRepositoryPort).saveUser(validUser);
        assertTrue(validUser.getEnabled());
    }

    @Test
    void testDisableUserById_success() throws UserException {
        validUser.enable();
        when(userRepositoryPort.findUserById(2L)).thenReturn(Optional.of(validUser));

        userService.disableUserById(2L);

        verify(userRepositoryPort).saveUser(validUser);
        assertFalse(validUser.getEnabled());
    }

}
