package com.example.practice.pay;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ShoppingMall {
    private Long accountId;
    private PaymentGateway paymentGateway;
    private Map<Long, Product> products = new HashMap<>();
    private Long generateProductId = 1L;

    public String pay(Long productId, Card card) {
        PayResponse response = paymentGateway.request(makePayRequest(productId, card));
        return response.getMessage();
    }

    public void addProduct(Product product) {
        products.put(generateProductId++, product);
    }

    public PayRequest makePayRequest(Long productId, Card card) {
        PayRequest request = new PayRequest();
        Product product = products.get(productId);
        request.setPayMethod("CARD");
        request.setAmount(product.getPrice());
        request.setBkCode(card.getBkCode());
        request.setSenderAccountId(card.getAccountId());
        request.setReceiveAccountId(this.accountId);
        return request;
    }
}
