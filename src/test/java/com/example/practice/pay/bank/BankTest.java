package com.example.practice.pay.bank;

import com.example.practice.pay.Account;
import com.example.practice.pay.KookminBank;
import com.example.practice.pay.PayResponse;
import com.example.practice.pay.PayStatus;
import com.example.practice.pay.mock.MockAccountConfig;
import com.example.practice.pay.mock.MockKookminBank;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class BankTest {

    private KookminBank bank;

    @Before
    public void setUp() {
        bank = new KookminBank();
    }

    @Test
    public void 계좌_생성_테스트() {
        Long accountId = bank.getACCOUNT_ID_GENERATE();

        bank.addAccount();

        assertThat(bank.getAccountList().get(accountId)).isNotNull();
    }

    @Test
    public void 계좌_생성_후_제너레이터가_증가하는지_테스트() {
        Long beforeId = bank.getACCOUNT_ID_GENERATE();

        int random = (int) (Math.random() * 10);
        for (int i = 0; i < random; i++) {
            bank.addAccount();
        }

        assertThat(bank.getACCOUNT_ID_GENERATE()).isEqualTo(beforeId + random);
    }

    @Test
    public void 송금_테스트() {
        //조건
        bank = new MockKookminBank();
        log.info("MockBank 생성");

        Map<Long, Account> accountList = bank.getAccountList();

        Account sender = accountList.get(MockAccountConfig.SENDER_ACCOUNT_ID);
        Account receiver = accountList.get(MockAccountConfig.RECEIVER_ACCOUNT_ID);
        log.info("sender_id={},sender_info={}", MockAccountConfig.SENDER_ACCOUNT_ID, sender);
        log.info("receiver_id={},receiver_info={}", MockAccountConfig.RECEIVER_ACCOUNT_ID, receiver);
        long senderBal = sender.getBalance();
        long receiverBal = receiver.getBalance();
        long price = new Random().nextInt(MockAccountConfig.INIT_BALANCE.intValue()) * 1L;

        //행위
        PayResponse response =
                bank.payProcess(MockAccountConfig.SENDER_ACCOUNT_ID, MockAccountConfig.RECEIVER_ACCOUNT_ID, price);

        //
        assertThat(response.getMessage()).isEqualTo(PayStatus.PAY_SUCCESS.getMessage());
        assertThat(sender.getBalance()).isEqualTo(senderBal - price);
        assertThat(receiver.getBalance()).isEqualTo(receiverBal + price);
    }

    @Test
    public void 송금_실패_테스트() {
        //조건
        bank = new MockKookminBank();
        log.info("MockBank 생성");

        Map<Long, Account> accountList = bank.getAccountList();

        Account sender = accountList.get(MockAccountConfig.SENDER_ACCOUNT_ID);
        Account receiver = accountList.get(MockAccountConfig.RECEIVER_ACCOUNT_ID);
        log.info("sender_id={},sender_info={}", MockAccountConfig.SENDER_ACCOUNT_ID, sender);
        log.info("receiver_id={},receiver_info={}", MockAccountConfig.RECEIVER_ACCOUNT_ID, receiver);
        long senderBal = sender.getBalance();
        long receiverBal = receiver.getBalance();

        //행위
        long price = MockAccountConfig.INIT_BALANCE + 10;
        System.out.println(price);
        PayResponse response =
                bank.payProcess(MockAccountConfig.SENDER_ACCOUNT_ID, MockAccountConfig.RECEIVER_ACCOUNT_ID, price);

        assertThat(response.getMessage()).isEqualTo(PayStatus.PAY_FAIL.getMessage());
    }

    @Test
    public void 존재하지_않는_계좌_isPresentAccount_테스트() {
        assertThat(bank.isPresentAccount(2000L)).isFalse();
    }

    @Test
    public void 존재하는_계좌_isPresentAccount_테스트() {
        bank = new MockKookminBank();
        assertThat(bank.isPresentAccount(2000L)).isFalse();
    }
}
