package com.example.practice.bowlingGame;

import com.example.practice.bowlingGame.exception.CalculatingFrameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class Frames {

    private Logger log = LoggerFactory.getLogger(Frames.class);
    private Frame[] frames = new Frame[Role.FRAME_COUNT];
    private int nowRoll = 1;    //현재 투구
    private int nowFrame = 1;   //현재 프레임
    private Integer[] scoreBoard = new Integer[11];


    public Frames() {
        createFrame();
    }

    public Frame getFrame(int index) {
        return frames[index - 1];
    }

    public int getScore(int frameNumber) {
        if (scoreBoard[frameNumber] != null)
            return scoreBoard[frameNumber];
        throw new CalculatingFrameException();
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
            cal(1);
        } else if (nowRoll == 2) {
            nowFrame++;
            nowRoll = 1;
            cal(1);
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

    public void cal(int frameNumber) {

        int next = frameNumber + 1;
        if (nowFrame <= frameNumber)
            return;

        if (scoreBoard[frameNumber] != null) {
            cal(next);
            return;
        }

        Frame frame = getFrame(frameNumber);
        Frame nextFrame = getFrame(next);
        switch (frame.getStatus()) {
            case STRIKE:
                if (next < nowFrame && nextFrame.getStatus() != FrameStatus.STRIKE) {
                    scoreBoard[frameNumber] = 10 + nextFrame.getScore();
                    cal(next);
                    return;
                } else if (next < nowFrame && nextFrame.getStatus() == FrameStatus.STRIKE) {
                    if (next + 1 < nowFrame) {
                        scoreBoard[frameNumber] = 10 + 10 + getFrame(next + 1).getScoreArray()[0];
                        cal(frameNumber + 1);
                        return;
                    } else
                        return;
                }
                return;
            case NORMAL:
                scoreBoard[frameNumber] = frame.getScore();
                cal(next);
                return;
            case SPARE:
                if (next < nowFrame) {
                    scoreBoard[frameNumber] = 10 + nextFrame.getScoreArray()[0];
                    cal(next);
                    return;
                }
                return;
        }
    }

    public void show() {
        System.out.println("-------------------------------------------------------");
        for (int i = 1; i <= 10; i++) {
            System.out.print("[ " + i + " ]");
        }
        System.out.println();
        for (Frame frame : frames) {
            switch (frame.getStatus()) {
                case STRIKE:
                    System.out.print("[ x ]");
                    break;
                case NORMAL:
                    System.out.print(Arrays.toString(frame.getScoreArray()).replaceAll(" ", ""));
                    break;
                case WAIT:
                    System.out.print("[ - ]");
                    break;
                case SPARE:
                    int[] arr = frame.getScoreArray();
                    System.out.print("[" + arr[0] + ",/]");
                    break;
            }
        }
        System.out.println();
        int total = 0;
        for (Integer score : scoreBoard) {
            if (score != null) {
                total += score;
                System.out.print("[" + total + "]");
            }
        }
        System.out.println("-------------------------------------------------------");
    }
}
