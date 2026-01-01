package com.jhernandez.backend.bazaar.infrastructure.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jhernandez.backend.bazaar.domain.exception.InvalidCredentialsException;
import com.jhernandez.backend.bazaar.domain.model.ErrorCode;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.UserEntity;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository.JpaUserRepository;

import lombok.RequiredArgsConstructor;

/*
 * Service for loading user details from the database.
 */
@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

    private final JpaUserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new InvalidCredentialsException(ErrorCode.AUTHENTICATION_CREDENTIALS_INVALID));

        return User.builder()
                .username(userEntity.getEmail())
                .password(userEntity.getPassword())
                .disabled(!userEntity.getEnabled())
                .accountExpired(false)
                .credentialsExpired(false)
                .accountLocked(false)
                .authorities(userEntity.getRole().getName())
                .build();
    }

}
