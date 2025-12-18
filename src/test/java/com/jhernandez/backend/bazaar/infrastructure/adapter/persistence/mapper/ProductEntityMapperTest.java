package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.jhernandez.backend.bazaar.domain.model.Product;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity.ProductEntity;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository.JpaProductRepository;

/*
 * Integration tests for ProductEntityMapper class.
 * Validates mapping between ProductEntity and Product domain model.
 * Ensures no infinite loops occur during mapping due to bidirectional relationships.
 */
@SpringBootTest
@ActiveProfiles("test")
class ProductEntityMapperTest {

    @Autowired
    private ProductEntityMapper productMapper;

    @Autowired
    private JpaProductRepository productRepository;

    @Transactional(readOnly = true)
    @Test
    void testMapProductEntityToDomain() {
        Optional<ProductEntity> entityOpt = productRepository.findById(1L);
        assertTrue(entityOpt.isPresent());

        ProductEntity entity = entityOpt.get();
        Product domain = productMapper.toDomain(entity);

        assertNotNull(domain);
        assertEquals(entity.getId(), domain.getId());
        assertEquals(entity.getName(), domain.getName());
        assertEquals(entity.getShop().getId(), domain.getShop().getId());
        assertNull(domain.getShop().getShopProducts());
    }

    @Transactional(readOnly = true)
    @Test
    void testMapProductEntityToEntity() {
        ProductEntity entity = productRepository.findById(1L).orElseThrow();
        Product domain = productMapper.toDomain(entity);
        domain.setId(77L);
        ProductEntity mappedEntity = productMapper.toEntity(domain);

        assertNotNull(mappedEntity);
        assertEquals(domain.getId(), mappedEntity.getId());
        assertEquals(domain.getName(), mappedEntity.getName());
        assertEquals(domain.getShop().getId(), mappedEntity.getShop().getId());
        assertNull(mappedEntity.getShop().getShopProducts());

    }
}