package com.example.practice.pay.mock;

import com.example.practice.pay.PaymentGateway;
import com.example.practice.pay.Product;
import com.example.practice.pay.ShoppingMall;

public class MockShoppingMall extends ShoppingMall {
    public MockShoppingMall(PaymentGateway mockPaymentGateway){
        setAccountId(2000L);
        setPaymentGateway(mockPaymentGateway);
        for(int i=0;i<10;i++){
            addProduct(new Product("상품"+i,1L*i));
        }
    }
}
