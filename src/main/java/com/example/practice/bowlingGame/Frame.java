package com.example.practice.bowlingGame;

public class Frame {

    private FrameStatus status;
    private int nowRoll = 0;
    public Roll[] rolls;
    private Frame next = null;

    public Frame() {
        status = FrameStatus.WAIT;
        rolls = new Roll[Role.FRAME_NORMAL_ROLL_COUNT];
    }

    public void addRoll(Roll roll, FrameStatus status) {
        rolls[nowRoll++] = roll;
        this.status = status;
    }

    public int getScore() {
        int score = 0;

        for (Roll roll : rolls) {
            if (roll != null)
                score += roll.getKnockDown();
        }
        return score;
    }

    public FrameStatus getStatus() {
        return status;
    }

    public int[] getScoreArray() {
        int[] arr = new int[rolls.length];
        for (int i = 0; i < rolls.length; i++) {
            if (rolls[i] != null)
                arr[i] = rolls[i].getKnockDown();
            else
                arr[i] = 0;
        }
        return arr;
    }

    public boolean check(int knockDown) {
        int score = getScore() + knockDown;

        if (score > Role.PIN_MAX_COUNT || score < 0)
            return false;
        return true;
    }

    public Frame getNext() {
        return next;
    }

    public void setNext(Frame next) {
        this.next = next;
    }
}
