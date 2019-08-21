package com.example.practice.bowlingGame;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TenFrameTest {

    private TenFrame tenFrame;

    @Before
    public void setUp() {
        tenFrame = new TenFrame();
    }

    @Test
    public void 생성자_테스트() {
        assertEquals(3, tenFrame.rolls.length);
    }

    @Test
    public void 체크함수_테스트() {
        tenFrame.addRoll(new Roll(10), FrameStatus.STRIKE);
        tenFrame.addRoll(new Roll(10), FrameStatus.STRIKE);
        assertTrue(tenFrame.check(10));
    }


}
