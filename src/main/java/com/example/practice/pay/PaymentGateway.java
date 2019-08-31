package com.example.practice.pay;

import java.util.HashMap;
import java.util.Map;

public class PaymentGateway {
    private Map<String, Bank> bkList = new HashMap<>();

    public PayResponse request(PayRequest request) {
        return null;
    }

    public void addBank(String bkCode, Bank bank) {
        if (bkList.containsKey(bkCode))
            throw new IllegalArgumentException();

        bkList.put(bkCode, bank);
    }

    public Map<String, Bank> getBkList() {
        return bkList;
    }

    public void setBkList(Map<String, Bank> bkList) {
        this.bkList = bkList;
    }
}
