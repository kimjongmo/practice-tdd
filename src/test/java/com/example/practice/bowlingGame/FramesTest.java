package com.example.practice.bowlingGame;

import com.example.practice.bowlingGame.exception.CalculatingFrameException;
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
        frames.record(8);
        frames.record(3);
    }

    @Test
    public void 프레임_스코어_판정_함수_스페어() {
        frames.record(8);
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
        assertEquals((firstRoll + secondRoll), frames.getScore(1));
    }

    @Test
    public void 스페어_이후_점수가_났을때() {
        frames.record(6);
        frames.record(4);
        frames.record(3);
        frames.record(6);
        assertTrue(13 == frames.getScore(1));
    }


    @Test
    public void 스트라이크_이후_노멀() {
        frames.record(10);
        frames.record(1);
        frames.record(1);
        assertEquals(12, frames.getScore(1));
    }

    @Test(expected = CalculatingFrameException.class)
    public void 스트라이크_이후_스트라이크() {
        frames.record(10);
        frames.record(10);
        assertEquals(0, frames.getScore(1));
    }

    @Test
    public void 스트라이크_이후_스트라이크_이후_노멀() {
        frames.record(10);
        frames.record(10);
        frames.record(7);
        frames.record(1);
        assertEquals(27, frames.getScore(1));
    }


}
