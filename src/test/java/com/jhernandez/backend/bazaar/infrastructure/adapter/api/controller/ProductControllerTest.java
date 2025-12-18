package com.jhernandez.backend.bazaar.infrastructure.adapter.api.controller;

import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/*
 * Integration tests for ProductController class.
 * Covers scenarios for accessing product-related endpoints with and without authentication.
 * Uses MockMvc for simulating HTTP requests and verifying responses.
 * Ensures proper access control and response structure.
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testFindAllEnabledProductsWithoutToken() throws Exception {
        mockMvc.perform(get("/api/products/enabled"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testFindAllProductsWithoutTokenUnauthorized() throws Exception {
        mockMvc.perform(get(PRODUCTS))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testFindAllProductsWithAdminRole() throws Exception {
        mockMvc.perform(get(PRODUCTS))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testFindProductById() throws Exception {
        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testFindProductsByCategoryId() throws Exception {
        mockMvc.perform(get("/api/products/category/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testFindRecentProducts() throws Exception {
        mockMvc.perform(get(PRODUCTS_RECENT))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testDisableProductById() throws Exception {
        mockMvc.perform(put("/api/products/disable/2"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/products/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.enabled").value(false));
    }

}
