package com.example.practice.pay.shoppingMall;

import com.example.practice.pay.PaymentGateway;
import com.example.practice.pay.Product;
import com.example.practice.pay.ShoppingMall;
import com.example.practice.pay.mock.MockPaymentGateway;
import com.example.practice.pay.mock.MockShoppingMall;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ShoppingMallTest {

    private ShoppingMall shoppingMall;

    @Before
    public void setUp() {
        PaymentGateway paymentGateway = new MockPaymentGateway();
        shoppingMall = new MockShoppingMall(paymentGateway);
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
}
