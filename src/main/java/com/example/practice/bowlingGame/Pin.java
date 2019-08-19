package com.example.practice.bowlingGame;

public class Pin {

    private int remain = 10;

    public void knockDown(int knockDown) {
        if (!checkScope(knockDown)) {
            throw new ArithmeticException();
        }
        remain -= knockDown;
    }

    public boolean checkScope(int knockDown) {
        if (knockDown < 0 || knockDown > remain) {
            return false;
        }
        return true;
    }

    public int getRemain() {
        return remain;
    }

}
