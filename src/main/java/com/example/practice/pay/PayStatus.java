package com.example.practice.pay;

import lombok.Getter;

@Getter
public enum PayStatus {
    PAY_SUCCESS(0, "결제 성공"),
    PAY_FAIL(1, "결제 실패"),
    INVALID_INFO(2, "잘못된 정보"),
    NOT_FOUND_ACCOUNT(3, "입력한 계좌정보가 잘못됬습니다"),
    ;
    private int id;
    private String message;

    PayStatus(int id, String message) {
        this.id = id;
        this.message = message;
    }


}
