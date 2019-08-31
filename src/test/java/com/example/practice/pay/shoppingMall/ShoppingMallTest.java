package com.example.practice.pay.shoppingMall;

import com.example.practice.pay.*;
import com.example.practice.pay.mock.MockCard;
import com.example.practice.pay.mock.MockPaymentGateway;
import com.example.practice.pay.mock.MockShoppingMall;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ShoppingMallTest {

    private Card card;
    private ShoppingMall shoppingMall;

    @Before
    public void setUp() {
        PaymentGateway paymentGateway = new MockPaymentGateway();
        shoppingMall = new MockShoppingMall();
        shoppingMall.setPaymentGateway(paymentGateway);
        card = new MockCard();
    }

    @Test
    public void 상품_추가_테스트() {
        //조건
        Product product = new Product("에어팟", 200000L);
        Long productId = shoppingMall.getGenerateProductId();

        //행위
        shoppingMall.addProduct(product);

        //결과
        Product registProduct = shoppingMall.getProducts().get(productId);
        assertThat(registProduct.getName()).isEqualTo(product.getName());
        assertThat(registProduct.getPrice()).isEqualTo(product.getPrice());
    }

    @Test
    public void 결제_요청_정보_생성_테스트() {
        //조건
        Long productId = 2L;

        //행위
        PayRequest request = shoppingMall.makePayRequest(productId, card);

        //결과
        Product product = shoppingMall.getProducts().get(productId);
        assertThat(request.getPayMethod()).isEqualTo("CARD");
        assertThat(request.getAmount()).isEqualTo(product.getPrice());
        assertThat(request.getBkCode()).isEqualTo(card.getBkCode());
        assertThat(request.getReceiveAccountId()).isEqualTo(shoppingMall.getAccountId());
        assertThat(request.getSenderAccountId()).isEqualTo(card.getAccountId());
    }
}
