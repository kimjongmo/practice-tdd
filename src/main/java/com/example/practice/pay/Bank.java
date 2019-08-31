package com.example.practice.pay;

public abstract class Bank {

    abstract public PayResponse payProcess(Long senderId, Long receiverId, Long price);

}
