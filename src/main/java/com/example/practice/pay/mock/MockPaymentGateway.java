package com.example.practice.pay.mock;

import com.example.practice.pay.PaymentGateway;

public class MockPaymentGateway extends PaymentGateway {
    public MockPaymentGateway(){
        addBank("kb",new MockKookminBank());
    }
}
