package com.example.practice.pay;

import lombok.Getter;

@Getter
public enum PayStatus {
    PAY_SUCCESS(0, "결제 성공"),
    PAY_FAIL(1, "결제 실패");

    private int id;
    private String message;

    PayStatus(int id, String message) {
        this.id = id;
        this.message = message;
    }


}
