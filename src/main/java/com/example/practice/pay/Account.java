package com.example.practice.pay;

public class Account {

    private long balance;

    public Account() {

    }

    public void deposit(long amount) {
        balance += amount;
    }

    public void withdraw(long amount) {
        if (amount > balance) {
            throw new IllegalArgumentException("잔액이 부족합니다.");
        }
        balance -= amount;
    }

    public long getBalance() {
        return balance;
    }
}
