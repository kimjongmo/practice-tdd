package com.example.practice.bowlingGame;

import com.example.practice.bowlingGame.exception.CalculatingFrameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Frames {

    private Logger log = LoggerFactory.getLogger(Frames.class);
    private Frame[] frames = new Frame[Role.FRAME_COUNT + 1];
    public int nowRoll = 1;    //현재 투구
    public int nowFrame = 1;   //현재 프레임
    private Integer[] scoreBoard = new Integer[11];

    public Frames() {
        createFrame();
    }

    public int getScore(int frameNumber) {
        if (scoreBoard[frameNumber] != null)
            return scoreBoard[frameNumber];
        throw new CalculatingFrameException();
    }

    public void createFrame() {
        for (int i = 1; i <= Role.FRAME_COUNT - 1; i++) {
            frames[i] = new Frame();
            frames[i - 1].setNext(frames[i]);
        }
        frames[Role.FRAME_COUNT] = new TenFrame();
    }

    public boolean record(int knockDown) {
        log.info("현재 투구:{},현재 프레임:{},쓰러뜨린 핀 수:{}", nowRoll, nowFrame, knockDown);

        if (!check(knockDown)) throw new IllegalArgumentException();

        FrameStatus status = judgement(knockDown);
        log.info("status:{}", status);

        recordFrame(new Roll(knockDown), status);

        return next(status);
    }

    public boolean next10() {
        if (nowRoll == 2 && frames[nowFrame].getScore() < 10) {
            nowFrame++;
            return true;
        }
        nowRoll++;
        return false;
    }

    public boolean next(FrameStatus status) {
        if (nowFrame == 10)
            return next10();
        if (status == FrameStatus.STRIKE || nowRoll == 2) {
            nowFrame++;
            nowRoll = 1;
            cal(1);
            return true;
        }
        nowRoll++;
        return false;
    }

    public boolean check(int knockDown) {
        return frames[nowFrame].check(knockDown);
    }

    public FrameStatus judgement(int knockDown) {
        if (nowFrame == 10)
            return judgement10(knockDown);
        if (nowRoll == 1 && knockDown == 10) return FrameStatus.STRIKE;
        else if (nowRoll == 2 && frames[nowFrame].getScore() + knockDown == 10) return FrameStatus.SPARE;
        return FrameStatus.NORMAL;
    }

    public FrameStatus judgement10(int knockDown) {
        if (knockDown == 10) return FrameStatus.STRIKE;
        else if (knockDown == 0) return FrameStatus.NORMAL;
        else if (nowRoll == 2 && frames[nowFrame].getScore() + knockDown == 10) return FrameStatus.SPARE;

        return FrameStatus.NORMAL;
    }

    public void recordFrame(Roll roll, FrameStatus status) {
        frames[nowFrame].addRoll(roll, status);
    }

    public void cal(int frameNumber) {

        int next = frameNumber + 1;

        if (nowFrame <= frameNumber)
            return;

        if (scoreBoard[frameNumber] != null) {
            cal(next);
            return;
        }

        Frame frame = frames[frameNumber];
        switch (frame.getStatus()) {
            case STRIKE: strike(frameNumber, frame); return;
            case NORMAL: normal(frameNumber, frame);return;
            case SPARE: spare(frameNumber, frame);return;
        }
    }

    public void strike(int number, Frame frame) {

        Frame next = frame.getNext();

        if (next.getStatus() != FrameStatus.STRIKE) {
            scoreBoard[number] = frame.getScore() + next.getScore();
            return;
        }
        Frame next2 = next.getNext();
        if (next2.getStatus() != FrameStatus.STRIKE) {
            scoreBoard[number] = frame.getScore() + next.getScore() + next2.getScore();
            return;
        }
        scoreBoard[number] = Role.FRMAE_10TH_MAX_SCORE;
    }

    public void normal(int number, Frame frame) {
        scoreBoard[number] = frame.getScore();
    }

    public void spare(int number, Frame frame) {
        scoreBoard[number] = frame.getScore() + frame.getNext().getScoreArray()[0];
    }

}
