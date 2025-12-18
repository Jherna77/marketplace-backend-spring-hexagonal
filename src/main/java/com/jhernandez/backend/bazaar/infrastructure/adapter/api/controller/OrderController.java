package com.jhernandez.backend.bazaar.infrastructure.adapter.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhernandez.backend.bazaar.application.port.OrderServicePort;
import com.jhernandez.backend.bazaar.domain.model.OrderStatus;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.dto.OrderStatusDto;
import com.jhernandez.backend.bazaar.infrastructure.adapter.api.mapper.OrderDtoMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.ORDERS;

/*
 * Controller for order-related endpoints.
 */
@RestController
@RequestMapping(ORDERS)
@CrossOrigin(originPatterns = "*")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderServicePort orderService;
    private final OrderDtoMapper orderDtoMapper;

    
    @PostMapping("/user/{userId}")
    public ResponseEntity<?> createOrderFromCart(@PathVariable Long userId) {
        log.info("Creating order");
        orderService.createOrderFromCart(userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/purchase/{userId}")
    public ResponseEntity<?> findPurchaseOrdersByUserId(@PathVariable Long userId) {
        log.info("Finding all orders for the user with ID ", userId);
        return ResponseEntity.ok(orderService.findPurchaseOrdersByUserId(userId).stream()
            .map(orderDtoMapper::toDto)
            .toList());
    }

    @GetMapping("/sale/{userId}")
    public ResponseEntity<?> findSaleOrdersByUserId(@PathVariable Long userId) {
        log.info("Finding all orders for the user with ID ", userId);
        return ResponseEntity.ok(orderService.findSaleOrdersByUserId(userId).stream()
            .map(orderDtoMapper::toDto)
            .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOrderById(@PathVariable Long id) {
        log.info("Finding order with ID {}", id);
        return ResponseEntity.ok(orderDtoMapper.toDto(orderService.findOrderById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable Long id, @RequestBody OrderStatusDto status) {
        log.info("Updating order with ID to status {}", id, status.getStatus());
        return ResponseEntity.ok(orderDtoMapper.toDto(orderService.updateOrderStatus(id, OrderStatus.valueOf(status.getStatus()))));
    }

}
