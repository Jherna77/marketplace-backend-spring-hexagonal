package com.jhernandez.backend.bazaar.infrastructure.adapter.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.ARG_CATEGORY;
import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.ARG_IMAGE;
import com.jhernandez.backend.bazaar.application.port.CategoryServicePort;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.CategoryDto;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper.CategoryDtoMapper;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper.ImageFileDtoMapper;

import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.CATEGORIES;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * CategoryController is a REST controller that handles HTTP requests related to categories.
 * It provides endpoints for creating, retrieving, updating, and deleting categories.
 * The controller uses the CategoryServicePort to perform the operations and the CategoryDtoMapper to convert between domain models and DTOs.
 */
@RestController
@RequestMapping(CATEGORIES)
@CrossOrigin(originPatterns = "*")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryServicePort categoryService;
    private final CategoryDtoMapper categoryDtoMapper;
    private final ImageFileDtoMapper imageFileDtoMapper;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> createCategory(
        @RequestPart(ARG_CATEGORY) CategoryDto category,
        @RequestPart(ARG_IMAGE) MultipartFile imageFile) {
        log.info("Creating category: {}", category.getName());
        categoryService.createCategory(
                    categoryDtoMapper.toDomain(category),
                    imageFileDtoMapper.toDomain(imageFile));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<CategoryDto> findAllCategories() {
        log.info("Finding all categories");
        return categoryDtoMapper.toDtoList(categoryService.findAllCategories());
    }

    @GetMapping("/enabled")
    public List<CategoryDto> findAllEnabledCategories() {
        log.info("Finding all enabled categories");
        return categoryDtoMapper.toDtoList(categoryService.findAllEnabledCategories());
    }

    @GetMapping("/random")
    public List<CategoryDto> findRandomEnabledCategories() {
        log.info("Finding random enabled categories");
        return categoryDtoMapper.toDtoList(categoryService.findRandomEnabledCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findCategoryById(@PathVariable Long id) {
        log.info("Finding category with ID {}", id);
        return ResponseEntity.ok(categoryDtoMapper.toDto(categoryService.findCategoryById(id)));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateCategory(
        @RequestPart(ARG_CATEGORY) CategoryDto category,
        @RequestPart(value = ARG_IMAGE, required = false) MultipartFile imageFile,
        @PathVariable Long id) {   
        log.info("Updating category {}", category.getName());
        categoryService.updateCategory(
                        categoryDtoMapper.toDomain(category),
                        imageFileDtoMapper.toDomain(imageFile));
        return ResponseEntity.ok().build();                
    }

    @PutMapping("/enable/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> enableCategory(@PathVariable Long id) {
        log.info("Enabling category with ID {}", id);
        categoryService.enableCategoryById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/disable/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> disableCategory(@PathVariable Long id) {
        log.info("Disabling category with ID {}", id);
        categoryService.disableCategoryById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        log.info("Deleting category with ID {}", id);
        categoryService.deleteCategoryById(id);
        return ResponseEntity.ok().build();
    }

}
