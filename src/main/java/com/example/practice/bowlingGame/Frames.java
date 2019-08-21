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
        }
        frames[Role.FRAME_COUNT] = new TenFrame();
    }

    public boolean record(int knockDown) {
        log.info("현재 투구:{},현재 프레임:{},쓰러뜨린 핀 수:{}", nowRoll, nowFrame, knockDown);
        if (!check(knockDown))
            throw new IllegalArgumentException();
        FrameStatus status = judgement(knockDown);
        log.info("status:{}", status);
        recordFrame(new Roll(knockDown), status);
        if (nowFrame != 10)
            return next(status);
        else
            return next10(status);
    }

    public boolean next10(FrameStatus status) {
        FrameStatus frameStatus = frames[nowFrame].getStatus();
        if (nowRoll == 2 && frameStatus != FrameStatus.STRIKE && frameStatus != FrameStatus.SPARE) {
            return true;
        }
        nowRoll++;
        return false;
    }

    public boolean next(FrameStatus status) {
        if (nowRoll == 1 && status == FrameStatus.STRIKE) {
            nowFrame++;
            nowRoll = 1;
            cal(1);
            return true;
        } else if (nowRoll == 2) {
            nowFrame++;
            nowRoll = 1;
            cal(1);
            return true;
        } else {
            nowRoll++;
            return false;
        }
    }

    public boolean check(int knockDown) {
        return frames[nowFrame].check(knockDown);
    }

    public FrameStatus judgement(int knockDown) {
        if (nowFrame!=10 && nowRoll == 1 && knockDown == 10) return FrameStatus.STRIKE;
        if (nowFrame!=10 && nowRoll == 2 && frames[nowFrame].getScore() + knockDown == 10) return FrameStatus.SPARE;
        if (nowFrame==10 && nowRoll ==1 && knockDown == 10) return FrameStatus.STRIKE;
        if (nowFrame==10 && nowRoll ==2 &&  frames[nowFrame].getStatus()!=FrameStatus.STRIKE) return FrameStatus.STRIKE;

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
        Frame nextFrame = frames[next];
        switch (frame.getStatus()) {
            case STRIKE:
                if (next < nowFrame && nextFrame.getStatus() != FrameStatus.STRIKE) {
                    scoreBoard[frameNumber] = 10 + nextFrame.getScore();
                    cal(next);
                    return;
                } else if (next < nowFrame && nextFrame.getStatus() == FrameStatus.STRIKE) {
                    if (next + 1 < nowFrame) {
                        scoreBoard[frameNumber] = 10 + 10 + frames[next + 1].getScoreArray()[0];
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
}
