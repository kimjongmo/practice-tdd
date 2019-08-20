package com.example.practice.bowlingGame;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FramesTest {
    private Frames frames;

    @Before
    public void setUp() {
        frames = new Frames();
    }

    @Test
    public void 스코어_체크_함수_첫번째_투기_10초과_실패() {
        assertFalse(frames.check(11));
    }

    @Test
    public void 스코어_체크_함수_첫번째_투기_0미만_실패() {
        assertFalse(frames.check(-1));
    }

    @Test
    public void 스코어_체크_함수_첫번째_투기_성공() {
        assertTrue(frames.check(8));
    }

    @Test
    public void 프레임_스코어_판정_함수_스트라이크() {
        int knockDown = 10;
        assertEquals(FrameStatus.STRIKE, frames.judgement(knockDown));
    }

    @Test(expected = IllegalArgumentException.class)
    public void 스코어_체크_함수_두번째_투기_10초과_실패() {
        frames.recordFrame(new Roll(8), 1, FrameStatus.NORMAL);
        frames.check(3);
    }

    @Test
    public void 프레임_스코어_판정_함수_스페어() {
        frames.recordFrame(new Roll(8), 1, FrameStatus.NORMAL);
        assertEquals(FrameStatus.SPARE, frames.judgement(2));
    }

    @Test
    public void 프레임_스코어_판정_함수_노말() {
        frames.recordFrame(new Roll(8), 1, FrameStatus.NORMAL);
        assertEquals(FrameStatus.NORMAL, frames.judgement(1));
    }

    @Test
    public void 기록한_스코어와_프레임에_기록된_스코어와_동일한지() {
        int firstRoll = 4;
        int secondRoll = 3;
        frames.record(firstRoll);
        frames.record(secondRoll);
        assertTrue(firstRoll + secondRoll == frames.score(1));
    }

    @Test
    public void 스페어_이후_점수가_났을때() {
        frames.record(6);
        frames.record(4);
        frames.record(3);
        frames.record(6);
        assertTrue(19 == frames.score(1));
    }


}
