package com.jhernandez.backend.bazaar.application.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.jhernandez.backend.bazaar.application.port.UserRepositoryPort;
import com.jhernandez.backend.bazaar.application.port.UserRoleRepositoryPort;
import com.jhernandez.backend.bazaar.application.port.UserServicePort;
import com.jhernandez.backend.bazaar.domain.exception.UserException;
import com.jhernandez.backend.bazaar.domain.model.ErrorCode;
import com.jhernandez.backend.bazaar.domain.model.User;
import com.jhernandez.backend.bazaar.domain.model.UserRole;

/**
 * This class implements the UserServicePort interface, which defines the
 * contract for user-related operations.
 * It can include methods for creating, retrieving, updating, and deleting
 * users.
 * The actual implementation of these methods would depend on the specific
 * requirements of the application.
 * The UserService class is responsible for implementing the business logic
 * related to users and returns the results to the controller.
 * It interacts with the data layer to perform CRUD operations on users and
 * handles any exceptions that may occur.
 */
public class UserService implements UserServicePort {

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    private static final String ZIP_CODE_PATTERN = "^[0-9]{5}$";
    private static final Long MASTER_ADMIN_ID = 1L;
   
    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepositoryPort userRoleRepositoryPort;

    public UserService(UserRepositoryPort userRepositoryPort, PasswordEncoder passwordEncoder,
            UserRoleRepositoryPort userRoleRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepositoryPort = userRoleRepositoryPort;
    }

    @Override
    public void createUser(User user) throws UserException {
        validateEmail(user.getEmail());
        validatePassword(user.getPassword());
        validateUserRole(user.getRole());
        validateUser(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.enable();
        userRepositoryPort.saveUser(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepositoryPort.findAllUsers();
    }

    @Override
    public User findUserById(Long id) throws UserException {
        return userRepositoryPort.findUserById(id)
                .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));
    }

    @Override
    public User findUserByEmail(String email) throws UserException {
        return userRepositoryPort.findUserByEmail(email)
                .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepositoryPort.existsByEmail(email);
    }

    @Override
    public void updateUser(User user) throws UserException {
        if (user.getId() == null)
            throw new UserException(ErrorCode.USER_ID_NOT_NULL);
        if (user.getId() == MASTER_ADMIN_ID)
            throw new UserException(ErrorCode.MASTER_ADMIN_UPDATE);
        if (!user.getEnabled())
            throw new UserException(ErrorCode.USER_DISABLED);

        validateUserRole(user.getRole());
        validateUser(user);

        User existingUser = findUserById(user.getId());
        existingUser.setRole(user.getRole());
        existingUser.setName(user.getName());
        existingUser.setSurnames(user.getSurnames());
        existingUser.setAddress(user.getAddress());
        existingUser.setCity(user.getCity());
        existingUser.setProvince(user.getProvince());
        existingUser.setZipCode(user.getZipCode());
        existingUser.setFavCategories(user.getFavCategories());
        userRepositoryPort.saveUser(existingUser);
    }

    @Override
    public void enableUserById(Long id) throws UserException {
        if (id == null)
            throw new UserException(ErrorCode.USER_ID_NOT_NULL);

        User existingUser = findUserById(id);
        if (existingUser.getEnabled())
            throw new UserException(ErrorCode.USER_ALREADY_ENABLED);
            
        existingUser.enable();
        userRepositoryPort.saveUser(existingUser);
    }

    @Override
    public void disableUserById(Long id) throws UserException {
        if (id == null)
            throw new UserException(ErrorCode.USER_ID_NOT_NULL);
        if (id == MASTER_ADMIN_ID)
            throw new UserException(ErrorCode.MASTER_ADMIN_DISABLE);
        
        User existingUser = findUserById(id);
        if (!existingUser.getEnabled())
            throw new UserException(ErrorCode.USER_ALREADY_DISABLED);
        existingUser.disable();
        userRepositoryPort.saveUser(existingUser);
    }

    @Override
    public void deleteUserById(Long id) throws UserException {
        throw new UserException(ErrorCode.OPERATION_NOT_ALLOWED);
    }

    private void validateEmail(String email) throws UserException {
        if (email == null || email.isEmpty())
            throw new UserException(ErrorCode.USER_EMAIL_REQUIRED);
        if (!email.matches(EMAIL_PATTERN))
            throw new UserException(ErrorCode.USER_EMAIL_INVALID);
        if (existsByEmail(email))
            throw new UserException(ErrorCode.USER_EMAIL_EXISTS);
    }

    private void validatePassword(String password) throws UserException {
        if (password == null || password.isEmpty())
            throw new UserException(ErrorCode.USER_PASSWORD_REQUIRED);
        if (!password.matches(PASSWORD_PATTERN))
            throw new UserException(ErrorCode.USER_PASSWORD_INVALID);
    }

    private void validateUserRole(UserRole userRole) throws UserException {
        if (userRole == null)
            throw new UserException(ErrorCode.USER_ROLE_REQUIRED);
        if (!userRole.getName().equals(userRoleRepositoryPort.findUserRoleById(userRole.getId()).getName()))
            throw new UserException(ErrorCode.USER_ROLE_INVALID);
    }

    private void validateUser(User user) throws UserException {
        if (user.getName() == null || user.getName().isEmpty())
            throw new UserException(ErrorCode.USER_NAME_REQUIRED);
        if (user.getSurnames() == null || user.getSurnames().isEmpty())
            throw new UserException(ErrorCode.USER_SURNAMES_REQUIRED);
        if (user.getAddress() == null || user.getAddress().isEmpty())
            throw new UserException(ErrorCode.USER_ADDRESS_REQUIRED);
        if (user.getCity() == null || user.getCity().isEmpty())
            throw new UserException(ErrorCode.USER_CITY_REQUIRED);
        if (user.getProvince() == null || user.getProvince().isEmpty())
            throw new UserException(ErrorCode.USER_PROVINCE_REQUIRED);
        if (user.getZipCode() == null || user.getZipCode().isEmpty())
            throw new UserException(ErrorCode.USER_ZIPCODE_REQUIRED);
        if (!user.getZipCode().matches(ZIP_CODE_PATTERN))
            throw new UserException(ErrorCode.USER_ZIPCODE_INVALID);
    }

}
