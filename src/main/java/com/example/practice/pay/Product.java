package com.example.practice.pay;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class Product {
    private String name;
    private Long price;

    public Product(String name, Long price) {
        if (price == null)
            throw new IllegalArgumentException("가격을 입력하세요");
        this.name = name;
        this.price = price;
    }

}
