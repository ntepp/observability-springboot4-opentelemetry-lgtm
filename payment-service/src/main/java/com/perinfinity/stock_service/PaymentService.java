package com.perinfinity.stock_service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class PaymentService {
    // Same logic as in Service A
public String callStockService(String orderId) {
    RestClient restClient = RestClient.create();
    return restClient.get()
            .uri("http://localhost:8083/stock/check?orderId=" + orderId)
            .retrieve()
                    .body(String.class);
}
    
}
