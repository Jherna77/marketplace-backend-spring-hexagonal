package com.jhernandez.backend.bazaar.domain.model;

import java.util.ArrayList;
import java.util.List;

import com.jhernandez.backend.bazaar.domain.exception.UserException;

/*
 * Model class representing a user entity.
 */
public class User {

    private Long id;
    private Boolean enabled;
    private UserRole role;
    private String email;
    private String password;
    private String name;
    private String surnames;
    private String address;
    private String city;
    private String province;
    private String zipCode;
    private List<Category> favCategories;
    private List<Product> favProducts;
    private List<Product> shopProducts;
    private List<Item> cart;

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

    public User(Long id, Boolean enabled, UserRole role, String email, String password, String name, String surnames,
            String address, String city, String province, String zipCode, List<Category> favCategories, List<Product> favProducts,
             List<Product> shopProducts, List<Item> cart) {
        this.id = id;
        this.enabled = enabled;
        this.role = role;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surnames = surnames;
        this.address = address;
        this.city = city;
        this.province = province;
        this.zipCode = zipCode;
        this.favCategories = (favCategories != null) ? favCategories : new ArrayList<>();
        this.favProducts = (favProducts != null) ? favProducts : new ArrayList<>();
        this.shopProducts = (shopProducts != null) ? shopProducts : new ArrayList<>();
        this.cart = (cart != null) ? cart : new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public UserRole getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurnames() {
        return surnames;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getZipCode() {
        return zipCode;
    }

    public List<Category> getFavCategories() {
        return favCategories;
    }

    public List<Product> getFavProducts() {
        return favProducts;
    }

    public List<Product> getShopProducts() {
        return shopProducts;
    }

    public List<Item> getCart() {
        return cart;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
        if (this.shopProducts != null)
            this.shopProducts.forEach(product -> product.setEnabled(enabled));
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setFavCategories(List<Category> favCategories) {
        this.favCategories = favCategories;
    }

    public void setFavProducts(List<Product> favourites) {
        this.favProducts = favourites;
    }

    public void setShopProducts(List<Product> products) {
        this.shopProducts = products;
    }

    public void setCart(List<Item> cart) {
        this.cart = cart;
    }

    public void enable() {
        this.enabled = true;
        if (this.shopProducts != null)
            this.shopProducts.forEach(product -> product.enable());
    }

    public void disable() throws UserException {
        if (!this.enabled)
            throw new UserException(ErrorCode.USER_ALREADY_DISABLED);
        this.enabled = false;
        if (this.shopProducts != null)
            this.shopProducts.forEach(product -> product.disable());
    }

    public void addProductToShop(Product product) throws UserException {
        for (Product shopProduct : this.shopProducts) {
            if (shopProduct.getId().equals(product.getId())) {
                throw new UserException(ErrorCode.SHOP_PRODUCT_ALREADY_EXISTS);
            }
        }
        this.shopProducts.add(product);
    }

    public void removeProductFromShop(Long productId) throws UserException {
        this.shopProducts.removeIf(product -> product.getId().equals(productId));
    }

    public void updateProductInShop(Product product) throws UserException {
        for (Product shopProduct : this.shopProducts) {
            if (shopProduct.getId().equals(product.getId())) {
                this.shopProducts.remove(shopProduct);
                this.shopProducts.add(product);
                return;
            }
        }
        throw new UserException(ErrorCode.SHOP_PRODUCT_NOT_FOUND);
    }

    public void addItemToCart(Item item) throws UserException {
        if (item.getProduct().getShop().getId().equals(this.id)) {
            throw new UserException(ErrorCode.CART_ITEM_FROM_OWN_SHOP);
        }
        for (Item cartItem : this.cart) {
            if (cartItem.getProduct().getId().equals(item.getProduct().getId())) {
                throw new UserException(ErrorCode.CART_ITEM_ALREADY_EXISTS);
            }
        }
        this.cart.add(item);
    }

    public void removeItemFromCart(Long itemId) {
        this.cart.removeIf(item -> item.getId().equals(itemId));
    }

    public void clearCart() {
        this.cart.clear();
    }

    public void addProductToFavourites(Product product) throws UserException {
        for (Product favourite : this.favProducts) {
            if (favourite.getId().equals(product.getId())) {
                throw new UserException(ErrorCode.FAVOURITE_PRODUCT_ALREADY_EXISTS);
            }
        }
        this.favProducts.add(product);
    }

    public void removeProductFromFavourites(Long productId) {
        this.favProducts.removeIf(product -> product.getId().equals(productId));
    }

    public void addCategoriesToFavourites(List<Category> categories) {
        for (Category category : categories) {
            if (!this.favCategories.contains(category)) {
                this.favCategories.add(category);
            }
        }
    }

}
