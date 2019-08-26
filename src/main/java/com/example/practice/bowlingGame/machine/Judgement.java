package com.example.practice.bowlingGame.machine;

import com.example.practice.bowlingGame.Frame;
import com.example.practice.bowlingGame.FrameStatus;

public class Judgement {

    public static FrameStatus judgement(int nowFrame, int nowRoll, int knockDown, Frame frame) {
        if (nowFrame == 10)
            return judgement10(nowRoll, knockDown, frame);
        if (nowRoll == 1 && knockDown == 10) return FrameStatus.STRIKE;
        else if (nowRoll == 2 && frame.getScore() + knockDown == 10) return FrameStatus.SPARE;
        return FrameStatus.NORMAL;
    }

    public static FrameStatus judgement10(int nowRoll, int knockDown, Frame frame) {
        if (knockDown == 10) return FrameStatus.STRIKE;
        else if (knockDown == 0) return FrameStatus.NORMAL;
        else if (nowRoll == 2 && frame.getScore() + knockDown == 10) return FrameStatus.SPARE;

        return FrameStatus.NORMAL;
    }

}
