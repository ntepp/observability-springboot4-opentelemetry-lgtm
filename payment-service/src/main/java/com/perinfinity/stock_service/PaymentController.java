package com.perinfinity.stock_service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/check")
    public ResponseEntity<String> processPayment(@RequestParam String orderId) {
        logger.info("Processing payment for order: {}", orderId);

        // 1. Simulating payment processing delay
        try { Thread.sleep(150); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        // Simulating a payment refusal for testing
        if (orderId.startsWith("ERR")) {
            logger.error("Payment refused for order {}", orderId);
            return ResponseEntity.status(402).body("Solde insuffisant");
        }

        // 2. Call to Service C (Stock)
        // The OTEL agent automatically injects the traceparent header here
        logger.info("Calling Stock-Service for order: {}", orderId);
        String stockResponse = paymentService.callStockService(orderId);

        return ResponseEntity.ok("Payment validated & " + stockResponse);
    }
}