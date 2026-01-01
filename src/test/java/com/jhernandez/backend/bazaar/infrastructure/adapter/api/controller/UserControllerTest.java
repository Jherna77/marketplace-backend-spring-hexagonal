package com.jhernandez.backend.bazaar.infrastructure.adapter.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.CONTENT_TYPE_JSON;
import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.USERS;
import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.REGISTER;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;

/*
 * Integration tests for UserController class.
 * Covers scenarios for creating, finding, and updating users.
 * Uses MockMvc for simulating HTTP requests and verifying responses.
 * Ensures proper access control and response structure.
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testAuthenticateUserAndReturnJwtToken() throws Exception {
        String jsonBody = """
                {
                    "email": "masteradmin@bazaar.es",
                    "password": "Pa$$W0rd"
                }
                """;

        mockMvc.perform(post("/login")
                .contentType(CONTENT_TYPE_JSON)
                .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());
    }

    @Test
    void testFindAllUsersWithoutToken() throws Exception {
        mockMvc.perform(get(USERS))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testFindAllUsersWithRoleAdmin() throws Exception {
        mockMvc.perform(get(USERS))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testFindUserById() throws Exception {
        mockMvc.perform(get("/api/users/1")
                .contentType(CONTENT_TYPE_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("masteradmin@bazaar.es"))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testDisableUserWithoutToken() throws Exception {
        mockMvc.perform(put("/api/users/disable/2"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testDisableUserWithRoleAdmin() throws Exception {
        mockMvc.perform(put("/api/users/disable/2"))
                .andExpect(status().isOk());
        
        mockMvc.perform(get("/api/users/2"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.enabled").value(false));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testDisableMasterAdminBadRequest() throws Exception {
        mockMvc.perform(put("/api/users/disable/1"))
                .andExpect(status().isBadRequest());

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.enabled").value(true));
    }

    @Test
    void testRegisterUser() throws Exception {
        String jsonBody = """
                {
                    "email": "newuser@bazaar.es",
                    "password": "Pa$$W0rd",
                    "name": "nameTest",
                    "surnames": "surnamesTest",
                    "address": "addressTest",
                    "city": "cityTest",
                    "province": "provinceTest",
                    "zipCode": "12345",
                    "role": {
                        "id": 3,
                        "name": "ROLE_CUSTOMER"
                    },
                    "favCategories": [
                        {
                            "id": 1,
                            "enabled": true,
                            "name": "Otros",
                            "imageUrl": "/api/images/00category7.jpg"
                        }
                    ]
                }
        """;

        mockMvc.perform(post(REGISTER)
                .contentType(CONTENT_TYPE_JSON)
                .content(jsonBody))
                .andExpect(status().isCreated());
    }

}
