package com.jhernandez.backend.bazaar.domain.model;

import java.time.LocalDateTime;

import com.jhernandez.backend.bazaar.domain.exception.OrderException;

/*
 * Model class representing an order entity.
 */
public class Order {

    private Long id;
    private OrderStatus status;
    private Item item;
    private User customer;
    private User shop;
    private LocalDateTime orderDate;

    public Order() {
        this.status = OrderStatus.PENDING;
    }

    public Order(Long id) {
        this.id = id;
        this.status = OrderStatus.PENDING;
    }

    public Order(Long id, Item item, User customer, User shop, LocalDateTime orderDate) {
        this.id = id;
        this.status = OrderStatus.PENDING;
        this.item = item;
        this.customer = customer;
        this.shop = shop;
        this.orderDate = orderDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public User getShop() {
        return shop;
    }

    public void setShop(User shop) {
        this.shop = shop;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public void confirm() throws OrderException {
        if (this.status != OrderStatus.PENDING) {
            throw new OrderException(ErrorCode.ORDER_CONFIRM_ERROR);
        }
        this.status = OrderStatus.CONFIRMED;
    }

    public void ship() throws OrderException {
        if (this.status != OrderStatus.CONFIRMED) {
            throw new OrderException(ErrorCode.ORDER_SHIP_ERROR);
        }
        this.status = OrderStatus.SHIPPED;
    }

    public void deliver() throws OrderException {
        if (this.status != OrderStatus.SHIPPED) {
            throw new OrderException(ErrorCode.ORDER_DELIVER_ERROR);
        }
        this.status = OrderStatus.DELIVERED;
    }

    public void cancel() throws OrderException {
        if (this.status == OrderStatus.SHIPPED ||  this.status == OrderStatus.DELIVERED ||
            this.status == OrderStatus.RETURNED) {
            throw new OrderException(ErrorCode.ORDER_CANCEL_ERROR);
        }
        this.status = OrderStatus.CANCELLED;
    }

    public void returnOrder() throws OrderException {
        if (this.status != OrderStatus.DELIVERED) {
            throw new OrderException(ErrorCode.ORDER_RETURN_ERROR);
        }
        this.status = OrderStatus.RETURNED;
    }

}
