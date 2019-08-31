package com.example.practice.pay;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PayResponse {
    private String message;

    public PayResponse(PayStatus status) {
        this.message = status.getMessage();
    }
}
