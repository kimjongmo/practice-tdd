package com.example.practice.pay;

import lombok.Getter;

@Getter
public class Account {

    private Long balance;

    public Account() {
        balance = 0L;
    }

    public void deposit(long amount) {
        balance += amount;
    }

    public boolean sendTo(Account receiver, long amount) {
        if (balance < amount)
            return false;
        synchronized (balance) {
            balance -= amount;
            receiver.deposit(amount);
            return true;
        }
    }
}
