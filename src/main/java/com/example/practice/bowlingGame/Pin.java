package com.example.practice.bowlingGame;

public class Pin {

    private int remain = Role.PIN_MAX_COUNT;

    public void knockDown(int knockDown) {
        if (!checkScope(knockDown)) {
            throw new ArithmeticException();
        }
        remain -= knockDown;
    }

    public boolean checkScope(int knockDown) {
        if (knockDown < Role.PIN_MIN_COUNT || knockDown > remain) {
            return false;
        }
        return true;
    }

    public int getRemain() {
        return remain;
    }

}
