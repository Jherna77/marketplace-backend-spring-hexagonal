package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.mapper;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.jhernandez.backend.bazaar.domain.model.User;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.UserEntity;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository.JpaUserRepository;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Integration tests for UserEntityMapper class.
 * Validates mapping between UserEntity and User domain model.
 * Ensures no infinite loops occur during mapping due to bidirectional relationships.
 */
@SpringBootTest
@ActiveProfiles("test")
class UserEntityMapperTest {

    @Autowired
    private UserEntityMapper userMapper;

    @Autowired
    private JpaUserRepository userRepository;

    @Transactional(readOnly = true)
    @Test
    void testUserEntityMapper() {
        Optional<UserEntity> userEntityOpt = userRepository.findByEmail("shop1@bazaar.es");
        assertTrue(userEntityOpt.isPresent());

        UserEntity entity = userEntityOpt.get();
        User user = userMapper.toDomain(entity);

        assertNotNull(user);
        assertEquals(entity.getId(), user.getId());
        assertEquals(entity.getEmail(), user.getEmail());
        assertNotNull(user.getShopProducts());
        assertEquals(entity.getShopProducts().size(), user.getShopProducts().size());
    }
}