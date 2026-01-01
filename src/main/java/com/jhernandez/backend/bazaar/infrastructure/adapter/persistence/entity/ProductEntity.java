package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

/*
 * Entity class representing a product record in the database.
 */
@Getter
@Setter
@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Boolean enabled;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Double shipping;

    @ManyToMany
    @JoinTable(name="products_categories",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id"),
        uniqueConstraints = { @UniqueConstraint(columnNames = {"product_id", "category_id"})})  
    private List<CategoryEntity> categories;

    @ElementCollection
    @CollectionTable(name = "product_images", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "image_url", nullable = false)
    private List<String> imagesUrl;

    @ManyToOne(optional = false)
    @JoinColumn(name = "shop_id", nullable = false)
    private UserEntity shop;

    @Column(nullable = false)
    private Integer sold;

    @Column(nullable = false)
    private Double rating;

    @Column(name = "rating_count", nullable = false)
    private Integer ratingCount;

    @Column(name = "created_at", nullable = false)    
    private LocalDateTime createdAt;

    @Column(name = "has_discount", nullable = false)
    private Boolean hasDiscount;

    @Column(name = "discount_price")
    private Double discountPrice;

    @Column(nullable = false)
    private Integer stock;

}
