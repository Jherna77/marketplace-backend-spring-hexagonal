package com.jhernandez.backend.bazaar.domain.model;

import com.jhernandez.backend.bazaar.domain.exception.ProductException;

/*
 * Model class representing an item in an order or cart.
 */
public class Item {

    private Long id;
    private Product product;
    private Double salePrice;
    private Double saleShipping;
    private Integer quantity;
    private Double totalPrice;

    public Item() {
    }

    public Item(Long id, Product product, Double salePrice, Double saleShipping, Integer quantity, Double totalPrice) {
        this.id = id;
        this.product = product;
        this.salePrice = salePrice;
        this.saleShipping = saleShipping;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public Double getSaleShipping() {
        return saleShipping;
    }

    public void setSaleShipping(Double saleShipping) {
        this.saleShipping = saleShipping;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void updateQuantity(Integer quantity) throws ProductException {
        if (quantity < 1) {
            throw new ProductException(ErrorCode.PRODUCT_INVALID_QUANTITY);
        }
        if (quantity > product.getStock()) {
            throw new ProductException(ErrorCode.PRODUCT_INSUFFICIENT_STOCK);
        }
        this.quantity = quantity;
        updateTotalPrice();
    }

    public void updateTotalPrice() {
        this.totalPrice = this.salePrice * this.quantity + this.saleShipping;
    }

    public Item clone() {
        return new Item(null, this.product, this.salePrice, this.saleShipping, this.quantity, this.totalPrice);
    }

}
