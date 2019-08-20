package com.example.practice.bowlingGame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Frames {

    private Logger log = LoggerFactory.getLogger(Frames.class);
    private Frame[] frames = new Frame[Role.FRAME_COUNT];
    private int nowRoll = 1;    //현재 투구
    private int nowFrame = 1;   //현재 프레임


    public Frames() {
        createFrame();
    }

    public Frame getFrame(int index) {
        return frames[index - 1];
    }

    private void createFrame() {
        for (int i = 0; i < Role.FRAME_COUNT - 1; i++) {
            frames[i] = new Frame(2);
        }
        frames[Role.FRAME_COUNT - 1] = new Frame(3);
    }

    public void record(int knockDown) {

        log.info("현재 투구:{},현재 프레임:{},쓰러뜨린 핀 수:{}", nowRoll, nowFrame, knockDown);

        if (!check(knockDown))
            throw new IllegalArgumentException();

        FrameStatus status = judgement(knockDown);
        log.info("status:{}", status);

        recordFrame(new Roll(knockDown), nowRoll, status);

        next(status);

    }

    public void next(FrameStatus status) {
        if (nowRoll == 1 && status == FrameStatus.STRIKE) {
            nowFrame++;
            nowRoll = 1;
        } else if (nowRoll == 2) {
            nowFrame++;
            nowRoll = 1;
        } else {
            nowRoll++;
        }
    }

    public boolean check(int knockDown) {
        int score = getFrame(nowFrame).getScore() + knockDown;

        if (score > Role.PIN_MAX_COUNT || score < 0)
            return false;

        return true;

    }

    public FrameStatus judgement(int knockDown) {
        if (nowRoll == 1 && knockDown == 10) return FrameStatus.STRIKE;
        if (nowRoll == 2 && getFrame(nowFrame).getScore() + knockDown == 10) return FrameStatus.SPARE;
        return FrameStatus.NORMAL;
    }

    public void recordFrame(Roll roll, int i, FrameStatus status) {
        getFrame(nowFrame).addRoll(roll, i, status);

    }

    public int score(int frameNumber) {
        return 0;
    }
}
