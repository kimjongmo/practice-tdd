package com.example.practice.bowlingGame;

public class Frame {

    private FrameStatus status;
    private Roll[] rolls;


    public Frame(int rollCount) {
        if (!isScope(rollCount))
            throw new IllegalArgumentException();
        status = FrameStatus.WAIT;
        rolls = new Roll[rollCount];
    }

    private boolean isScope(int rollCount) {
        if (rollCount == Role.FRAME_NORMAL_ROLL_COUNT
                || rollCount == Role.FRAME_10TH_ROLL_COUNT)
            return true;
        return false;
    }

    public void addRoll(Roll roll, int index,FrameStatus status) {
        rolls[index-1] = roll;
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

    public void setStatus(FrameStatus frameStatus) {
        this.status = frameStatus;
    }

}
