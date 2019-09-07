package com.example.practice.pay;

import java.util.HashMap;
import java.util.Map;

public class PaymentGateway {
    private Map<String, Bank> bkList = new HashMap<>();

    public PayResponse request(PayRequest request) {
        if (!(isEmpty(request) && isRightInfo(request)))
            return new PayResponse(PayStatus.INVALID_INFO);

        Bank bank = bkList.get(request.getBkCode());
        return bank.payProcess(request.getSenderAccountId(), request.getReceiveAccountId(), request.getAmount());
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

    public boolean isEmpty(PayRequest payRequest) {
        if (payRequest.getAmount() == null
                || payRequest.getBkCode() == null
                || payRequest.getPayMethod() == null
                || payRequest.getSenderAccountId() == null
                || payRequest.getReceiveAccountId() == null)
            return false;
        return true;
    }

    public boolean isRightInfo(PayRequest payRequest) {
        if (payRequest.getAmount() < 0
                || !bkList.containsKey(payRequest.getBkCode()))
            return false;
        return true;
    }
}
