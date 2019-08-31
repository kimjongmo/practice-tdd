package com.example.practice.pay;

import java.util.HashMap;
import java.util.Map;

public class KookminBank extends Bank {
    private Map<Long, Account> accountList = new HashMap<>();

    @Override
    public PayResponse payProcess(Long senderId, Long receiverId, Long price) {
        Account sender = accountList.get(senderId);
        Account receiver = accountList.get(receiverId);
        sender.withdraw(price);
        receiver.deposit(price);
        return new PayResponse("결제 성공");
    }
}
