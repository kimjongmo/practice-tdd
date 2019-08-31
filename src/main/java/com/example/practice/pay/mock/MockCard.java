package com.example.practice.pay.mock;

import com.example.practice.pay.Card;

public class MockCard extends Card {
    public MockCard() {
        setBkCode("KB");
        setAccountId(MockAccountConfig.SENDER_ACCOUNT_ID);
    }
}
