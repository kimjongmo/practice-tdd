package com.example.practice.bowlingGame;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FrameTest {

    @Test
    public void 생성자_성공_테스트() {
        Frame frame = new Frame();
        assertEquals(0, frame.getScore());
    }

    @Test
    public void 쓰러뜨린_핀의_점수가_스페어일때() {
        Frame frame = new Frame();
        int knockDown = 7;
        frame.addRoll(new Roll(7),FrameStatus.NORMAL);
        assertEquals(knockDown, frame.getScore());
    }


}
