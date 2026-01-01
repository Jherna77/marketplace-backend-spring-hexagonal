package com.jhernandez.backend.bazaar.infrastructure.adapter.api.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhernandez.backend.bazaar.domain.model.OrderStatus;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.jhernandez.backend.bazaar.infrastructure.configuration.Values.STATUSES;

import java.util.Arrays;

/*
 * Controller for order status-related endpoints.
 */
@RestController
@RequestMapping(STATUSES)
@CrossOrigin(originPatterns = "*")
@RequiredArgsConstructor
@Slf4j
public class OrderStatusController {

    @GetMapping
    public OrderStatus[] findAllOrderStatuses() {
        log.info("Finding all order statuses (excluding PENDING)");
        return Arrays.stream(OrderStatus.values())
                .filter(status -> status != OrderStatus.PENDING)
                .toArray(OrderStatus[]::new);
    }

}
