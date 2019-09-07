package com.example.practice.pay;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PayRequest {

    private String payMethod;
    private Long amount;
    private String bkCode;
    private Long senderAccountId;
    private Long receiveAccountId;
}
