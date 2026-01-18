package com.perinfinity.order_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService; // Service injection

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> createOrder(@PathVariable String id) {
        logger.info("Receiving order for ID: {}", id);
        // 1. Call to Service B via OrderService
        String paymentStatus = orderService.processOrder(id);

        // 2. Final response
        return ResponseEntity.ok("Order " + id + " processed. Payment status: " + paymentStatus);
    }
}
