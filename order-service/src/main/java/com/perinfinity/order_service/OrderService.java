package com.perinfinity.order_service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class OrderService {
    private final RestClient restClient;

    public OrderService(RestClient.Builder builder) {
        this.restClient = builder.baseUrl("http://localhost:8082").build();
    }

    public String processOrder(String id) {
        // The agent will automatically add the 'traceparent' header here
        return restClient.get()
                .uri(uriBuilder -> uriBuilder.path("/payments/check")
                        .queryParam("orderId", id).build())
                .retrieve()
                .body(String.class);
    }
}
