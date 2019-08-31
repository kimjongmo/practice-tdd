package com.example.practice.pay.mock;

import com.example.practice.pay.PaymentGateway;
import com.example.practice.pay.Product;
import com.example.practice.pay.ShoppingMall;

public class MockShoppingMall extends ShoppingMall {
    public MockShoppingMall() {
        setAccountId(MockAccountConfig.RECEIVER_ACCOUNT_ID);
        for (int i = 0; i < 10; i++) {
            addProduct(new Product("상품" + i, 1L * i));
        }
    }
}
