package com.jhernandez.backend.bazaar.domain.model;

/*
 * Model class representing a user role entity.
 */
public class UserRole {

    private Long id;
    private String name;

    public UserRole(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}
