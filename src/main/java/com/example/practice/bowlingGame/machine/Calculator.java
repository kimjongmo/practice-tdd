package com.example.practice.bowlingGame.machine;

import com.example.practice.bowlingGame.Frame;
import com.example.practice.bowlingGame.FrameStatus;
import com.example.practice.bowlingGame.Role;

public class Calculator {

    public static int cal(int frameNumber, Frame frame) {
        switch (frame.getStatus()) {
            case STRIKE:
                return strike(frameNumber, frame);
            case NORMAL:
                return normal(frameNumber, frame);
            case SPARE:
                return spare(frameNumber, frame);
            default:
                return 0;
        }
    }

    public static int strike(int number, Frame frame) {

        Frame next = frame.getNext();

        if (next.getStatus() != FrameStatus.STRIKE) {
            return frame.getScore() + next.getScore();
        }
        Frame next2 = next.getNext();
        if (next2.getStatus() != FrameStatus.STRIKE) {
            return frame.getScore() + next.getScore() + next2.getScore();
        }
        return Role.FRMAE_10TH_MAX_SCORE;
    }

    public static int normal(int number, Frame frame) {
        return frame.getScore();
    }

    public static int spare(int number, Frame frame) {
        return frame.getScore() + frame.getNext().getScoreArray()[0];
    }
}
