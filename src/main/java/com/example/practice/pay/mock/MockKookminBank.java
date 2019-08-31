package com.example.practice.pay.mock;

import com.example.practice.pay.Account;
import com.example.practice.pay.KookminBank;

import java.util.Map;

public class MockKookminBank extends KookminBank {
    public MockKookminBank() {
        Map<Long, Account> accountList = getAccountList();

        Account sender = new Account();
        sender.deposit(MockAccountConfig.INIT_BALANCE);

        Account receiver = new Account();
        receiver.deposit(MockAccountConfig.INIT_BALANCE);

        accountList.put(MockAccountConfig.SENDER_ACCOUNT_ID, sender);
        accountList.put(MockAccountConfig.RECEIVER_ACCOUNT_ID, receiver);
    }
}
