package com.example.practice.pay;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Getter
@Slf4j
public class KookminBank extends Bank {
    private Map<Long, Account> accountList = new HashMap<>();
    private Long ACCOUNT_ID_GENERATE = 1000L;

    @Override
    public PayResponse payProcess(Long senderId, Long receiverId, Long price) {
        log.info("senderId = {}, receiverId = {}, price = {}", senderId, receiverId, price);

        if (!(isPresentAccount(senderId) && isPresentAccount(receiverId)))
            return new PayResponse(PayStatus.NOT_FOUND_ACCOUNT);

        Account sender = accountList.get(senderId);
        Account receiver = accountList.get(receiverId);
        boolean result = sender.sendTo(receiver, price);
        PayStatus status = result ? PayStatus.PAY_SUCCESS : PayStatus.PAY_FAIL;
        return new PayResponse(status);
    }

    public boolean isPresentAccount(Long id) {
        if (!accountList.containsKey(id))
            return false;
        return true;
    }

    public Long addAccount() {
        if (accountList.get(ACCOUNT_ID_GENERATE) != null) {
            ACCOUNT_ID_GENERATE++;
            throw new RuntimeException();
        }
        accountList.put(ACCOUNT_ID_GENERATE++, new Account());
        return ACCOUNT_ID_GENERATE - 1;
    }
}
