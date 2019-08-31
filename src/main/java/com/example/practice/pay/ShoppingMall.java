package com.example.practice.pay;

import java.util.HashMap;
import java.util.Map;

public class ShoppingMall {
    private Long accountId;
    private PaymentGateway paymentGateway;
    private Map<Long, Product> products = new HashMap<>();
    private Long generateProductId = 1L;

    public String pay(Long productId, Card card) {
        return null;
    }

    public void addProduct(Product product) {
        products.put(generateProductId++, product);
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public PaymentGateway getPaymentGateway() {
        return paymentGateway;
    }

    public void setPaymentGateway(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public Map<Long, Product> getProducts() {
        return products;
    }

    public void setProducts(Map<Long, Product> products) {
        this.products = products;
    }

    public Long getGenerateProductId() {
        return generateProductId;
    }

    public void setGenerateProductId(Long generateProductId) {
        this.generateProductId = generateProductId;
    }
}
