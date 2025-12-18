package com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/*
 * Data Transfer Object for user request information.
 */
@Getter
@Setter
public class UserRequestDto {

    private Long id;
    private Boolean enabled;
    private UserRoleDto role;
    private String email;
    private String password;    
    private String name;
    private String surnames;
    private String address;
    private String city;
    private String province;
    private String zipCode;
    private List<CategoryDto> favCategories;
    
}
