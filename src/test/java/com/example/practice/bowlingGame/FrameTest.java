package com.example.practice.bowlingGame;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FrameTest {

    @Test(expected = IllegalArgumentException.class)
    public void 생성자_실패_테스트_MAX_이상() {
        new Frame(4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void 생성자_실패_테스트_MIN_이하() {
        new Frame(1);
    }

    @Test
    public void 생성자_성공_테스트() {
        Frame frame = new Frame(2);
        assertEquals(0, frame.getScore());
    }

    @Test
    public void 쓰러뜨린_핀의_점수가_스페어일때() {
        Frame frame = new Frame(2);
        int knockDown = 7;
        frame.addRoll(new Roll(7),1,FrameStatus.NORMAL);
        assertEquals(knockDown, frame.getScore());
    }


}
