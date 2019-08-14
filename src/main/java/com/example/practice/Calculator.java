package com.example.practice;

public class Calculator {

    public static int cal(int[] number, char op) {
        int ret = 0;
        switch (op) {
            case '+':
                ret = add(number[0], number[1]);
                break;
            case '-':
                ret = minus(number[0], number[1]);
                break;
            case '*':
                ret = mul(number[0], number[1]);
                break;
            case '/':
                ret = div(number[0], number[1]);
                break;
        }

        return ret;
    }

    public static int add(int num1, int num2) {
        return num1 + num2;
    }

    public static int minus(int num1, int num2) {
        return num1 - num2;
    }

    public static int mul(int num1, int num2) {
        return num1 * num2;
    }

    public static int div(int num1, int num2) {
        return num1 / num2;
    }
}
