package com.jhernandez.backend.bazaar.infrastructure.adapter.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/*
 * Entity class representing an item record in the database.
 */
@Getter
@Setter
@Entity
@Table(name = "items")
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;
    
    @Column(name = "sale_price", nullable = false)
    private Double salePrice;

    @Column(name = "sale_shipping", nullable = false)
    private Double saleShipping;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;
    
}
