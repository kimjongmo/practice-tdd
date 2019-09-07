package com.example.practice.pay.gateway;

import com.example.practice.pay.PayRequest;
import com.example.practice.pay.PayResponse;
import com.example.practice.pay.PayStatus;
import com.example.practice.pay.PaymentGateway;
import com.example.practice.pay.mock.MockAccountConfig;
import com.example.practice.pay.mock.MockPaymentGateway;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GatewayTest {

    PaymentGateway gateway;
    PayRequest payRequest;

    @Before
    public void setUp() {
        gateway = new MockPaymentGateway();
        payRequest = new PayRequest();
    }


    @Test
    public void 잘못된_요청_처리_isEmpty_테스트() {
        payRequest = new PayRequest();
        payRequest.setBkCode("bk");
        payRequest.setReceiveAccountId(MockAccountConfig.RECEIVER_ACCOUNT_ID);
        payRequest.setSenderAccountId(MockAccountConfig.SENDER_ACCOUNT_ID);
        payRequest.setPayMethod("CARD");

        boolean bool = gateway.isEmpty(payRequest);
        assertThat(bool).isFalse();
    }

    @Test
    public void 모든_정보가_입력되었을_때_isEmpty_테스트() {
        payRequest = new PayRequest();
        payRequest.setBkCode("bk");
        payRequest.setReceiveAccountId(MockAccountConfig.RECEIVER_ACCOUNT_ID);
        payRequest.setSenderAccountId(MockAccountConfig.SENDER_ACCOUNT_ID);
        payRequest.setPayMethod("CARD");
        payRequest.setAmount(-1L);

        boolean bool = gateway.isEmpty(payRequest);
        assertThat(bool).isTrue();
    }

    @Test
    public void 잘못된_요청_응답() {
        payRequest = new PayRequest();
        payRequest.setReceiveAccountId(MockAccountConfig.RECEIVER_ACCOUNT_ID);
        payRequest.setSenderAccountId(MockAccountConfig.SENDER_ACCOUNT_ID);
        payRequest.setPayMethod("CARD");

        PayResponse payResponse = gateway.request(payRequest);
        assertThat(payResponse.getMessage()).isEqualTo(PayStatus.INVALID_INFO.getMessage());
    }

    @Test
    public void 지원하지_않는_은행_isRightInfo_테스트() {
        payRequest = new PayRequest();
        payRequest.setAmount(3000L);
        payRequest.setBkCode("sh");
        payRequest.setReceiveAccountId(MockAccountConfig.RECEIVER_ACCOUNT_ID);
        payRequest.setSenderAccountId(MockAccountConfig.SENDER_ACCOUNT_ID);
        payRequest.setPayMethod("CARD");

        boolean bool = gateway.isRightInfo(payRequest);
        assertThat(bool).isFalse();
    }

    public void 잘못된_금액_입력_isRightInfo_테스트() {
        payRequest = new PayRequest();
        payRequest.setAmount(-1L);
        payRequest.setBkCode("bk");
        payRequest.setReceiveAccountId(MockAccountConfig.RECEIVER_ACCOUNT_ID);
        payRequest.setSenderAccountId(MockAccountConfig.SENDER_ACCOUNT_ID);
        payRequest.setPayMethod("CARD");

        boolean bool = gateway.isRightInfo(payRequest);
        assertThat(bool).isFalse();
    }

    public void 금액이_양의_정수_지원하는_은행일_때_isRightInfo_테스트() {
        payRequest = new PayRequest();
        payRequest.setAmount(1L);
        payRequest.setBkCode("bk");
        payRequest.setReceiveAccountId(MockAccountConfig.RECEIVER_ACCOUNT_ID);
        payRequest.setSenderAccountId(MockAccountConfig.SENDER_ACCOUNT_ID);
        payRequest.setPayMethod("CARD");

        boolean bool = gateway.isRightInfo(payRequest);
        assertThat(bool).isTrue();
    }

}
