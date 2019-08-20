package com.example.practice.bowlingGame;

public class Roll {
    private int knockDown = 0;

    public Roll(int knockDown) {
        if (!isScope(knockDown))
            throw new IllegalArgumentException();
        this.knockDown = knockDown;
    }

    public boolean isScope(int knockDown) {
        if (knockDown < 0 || knockDown > 10) {
            return false;
        }
        return true;
    }

    public int getKnockDown() {
        return knockDown;
    }
}
