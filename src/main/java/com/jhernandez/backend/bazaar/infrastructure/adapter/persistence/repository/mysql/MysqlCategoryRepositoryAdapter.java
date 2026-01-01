package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository.mysql;

// import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.DISABLED_ITEM;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jhernandez.backend.bazaar.application.port.CategoryRepositoryPort;
import com.jhernandez.backend.bazaar.domain.model.Category;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.mapper.CategoryEntityMapper;
import com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.repository.JpaCategoryRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * Repository adapter for Category.
 * Implements CategoryRepositoryPort to provide persistence operations using JpaCategoryRepository.
 * Uses CategoryEntityMapper to convert between domain model and entity.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MysqlCategoryRepositoryAdapter implements CategoryRepositoryPort {

    private final JpaCategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Transactional
    @Override
    public void saveCategory(Category category) {
        log.info("Saving category {}", category.getName());
        categoryRepository.save(categoryEntityMapper.toEntity(category));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Category> findAllCategories() {
        log.info("Finding all categories {}");
        return categoryRepository.findAll().stream()
                .map(categoryEntityMapper::toDomain)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Category> findAllEnabledCategories() {
        log.info("Finding all enabled categories {}");
        return categoryRepository.findAllEnabled().stream()
                .map(categoryEntityMapper::toDomain)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Category> findRandomEnabledCategories() {
        log.info("Finding random enabled categories");
        return categoryRepository.findRandomEnabled().stream()
                .map(categoryEntityMapper::toDomain)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Category> findCategoryById(Long id) {
        log.info("Finding category with ID {}", id);
        return categoryRepository.findById(id).map(categoryEntityMapper::toDomain);
    }

    @Transactional(readOnly = true)
    @Override
    public Boolean existsByName(String name) {
        return categoryRepository.existsByName(name);
    }

    @Transactional
    @Override
    public void deleteCategoryById(Long id) {
        log.info("Deleting category with ID {}", id);
        categoryRepository.deleteById(id);
    }

}
