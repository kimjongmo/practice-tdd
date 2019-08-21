package com.example.practice.bowlingGame;

public class TenFrame extends Frame {

    public TenFrame() {
        rolls = new Roll[Role.FRAME_10TH_ROLL_COUNT];
    }

    @Override
    public boolean check(int knockDown) {
        int score = getScore() + knockDown;
        if (score > Role.FRMAE_10TH_MAX_SCORE || score < 0)
            return false;
        return true;
    }
}
