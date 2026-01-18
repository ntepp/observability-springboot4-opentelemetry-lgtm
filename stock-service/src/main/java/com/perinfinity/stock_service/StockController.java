package com.perinfinity.stock_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stock")
public class StockController {

    private static final Logger logger = LoggerFactory.getLogger(StockController.class);

    @GetMapping("/check")
    public ResponseEntity<String> checkStock(@RequestParam String orderId) {
        logger.info("Checking stock for order: {}", orderId);

        // Simulating a delay
        try { Thread.sleep(100); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        // ERROR SCENARIO FOR POC
        if ("999".equals(orderId)) {
            logger.error("CRITICAL ERROR: Stock exhausted for item 999");
            throw new RuntimeException("EMPTY_STOCK_EXCEPTION: Out of stock for critical item.");
        }

        return ResponseEntity.ok("Stock available");
    }
}