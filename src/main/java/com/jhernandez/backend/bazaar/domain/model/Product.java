package com.jhernandez.backend.bazaar.domain.model;

import java.time.LocalDateTime;
import java.util.List;

import com.jhernandez.backend.bazaar.domain.exception.ProductException;

/*
 * Model class representing a product entity.
 */
public class Product {

    private Long id;
    private Boolean enabled;
    private String name;
    private String description;
    private Double price;
    private Double shipping;
    private List<Category> categories;
    private List<String> imagesUrl;
    private User shop;
    private Integer sold;
    private Double rating;
    private Integer ratingCount;
    private LocalDateTime createdAt;
    private Boolean hasDiscount;
    private Double discountPrice;
    private Integer stock;

    public Product() {
    }

    public Product(Long id) {
        this.id = id;
    }

    public Product(Long id, Boolean enabled, String name, String description, Double price, Double shipping,
            List<Category> categories, List<String> imagesUrl, User shop, Integer sold, Double rating,
            Integer ratingCount, LocalDateTime createdAt, Boolean hasDiscount, Double discountPrice, Integer stock) {
        this.id = id;
        this.enabled = enabled;
        this.name = name;
        this.description = description;
        this.price = price;
        this.shipping = shipping;
        this.categories = categories;
        this.imagesUrl = imagesUrl;
        this.shop = shop;
        this.sold = sold;
        this.rating = rating;
        this.ratingCount = ratingCount;
        this.createdAt = createdAt;
        this.hasDiscount = hasDiscount;
        this.discountPrice = discountPrice;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public Double getShipping() {
        return shipping;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public List<String> getImagesUrl() {
        return imagesUrl;
    }

    public User getShop() {
        return shop;
    }

    public Integer getSold() {
        return sold;
    }

    public Double getRating() {
        return rating;
    }

    public Integer getRatingCount() {
        return ratingCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Boolean getHasDiscount() {
        return hasDiscount;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setShipping(Double shipping) {
        this.shipping = shipping;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void setImagesUrl(List<String> imagesUrl) {
        this.imagesUrl = imagesUrl;
    }

    public void setShop(User shop) {
        this.shop = shop;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public void setRatingCount(Integer ratingCount) {
        this.ratingCount = ratingCount;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setCreatedAtNow() {
        this.createdAt = LocalDateTime.now();;
    }

    public void setHasDiscount(Boolean hasDiscount) {
        this.hasDiscount = hasDiscount;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void enable() {
        this.enabled = true;
    }

    public void disable() {
        this.enabled = false;
    }

    public void addCategory(Category category) {
        this.categories.add(category);
    }

    public void removeCategory(Long categoryId, Category defaultCategory) {
        this.categories
                .removeIf(category -> category.getId().equals(categoryId));
        if (this.categories.isEmpty())
            addCategory(defaultCategory);
    }

    public void addImageUrl(String imageUrl) {
        this.imagesUrl.add(imageUrl);
    }

    public void removeImageUrl(String imageUrl) {
        this.imagesUrl.remove(imageUrl);
    }

    public void addSold(Integer quantity) throws ProductException {
        if (this.stock < quantity) {
            throw new ProductException(ErrorCode.PRODUCT_INSUFFICIENT_STOCK);
        }

        this.sold += quantity;
        updateStock(quantity);
    }

    public void updateStock(Integer quantity) {
        this.stock -= quantity;
        if (this.stock < 0) {
            this.stock = 0;
        }
    }

    public void calculateRating(List<Integer> ratings) {
        if (ratings == null || ratings.isEmpty()) {
            this.rating = 0.0;
            this.ratingCount = 0;
            return;
        }
        this.ratingCount = ratings.size();
        this.rating = ratings.stream().mapToDouble(Integer::doubleValue).sum() / this.ratingCount;
    }

}
