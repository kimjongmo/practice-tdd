package com.example.practice.bowlingGame.frames;

import com.example.practice.bowlingGame.FrameStatus;
import com.example.practice.bowlingGame.Frames;
import com.example.practice.bowlingGame.exception.CalculatingFrameException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FramesRecordTest {
    private Frames frames;

    @Before
    public void setUp() {
        frames = new Frames();
    }

    @Test(expected = IllegalArgumentException.class)
    public void 스코어_체크_함수_두번째_투기_10초과_실패() {
        frames.record(8);
        frames.record(3);
    }

    @Test
    public void 프레임_스코어_판정_함수_스페어() {
        frames.record(8);
        Assert.assertEquals(FrameStatus.SPARE, frames.judgement(2));
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

    @Test
    public void 스트라이크_터키() {
        frames.record(10);
        frames.record(10);
        frames.record(10);
        frames.record(5);
        frames.record(3);
        assertEquals(30, frames.getScore(1));
    }

    @Test
    public void 열번째_프레임_노멀() {
        frames.nowFrame = 10;
        frames.record(5);
        assertTrue(frames.record(4));
    }

    @Test
    public void 열번째_프레임_스페어(){
        frames.nowFrame = 10;
        frames.record(5);
        assertFalse(frames.record(5));
    }
    @Test
    public void 열번째_프레임_처음_스트라이크_이후_노멀(){
        frames.nowFrame = 10;
        frames.record(10);
        assertFalse(frames.record(5));
    }


}
