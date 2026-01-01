package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


/*
 * Entity class representing a category record in the database.
 */
@Getter
@Setter
@Entity
@Table(name = "categories")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Boolean enabled;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

}
