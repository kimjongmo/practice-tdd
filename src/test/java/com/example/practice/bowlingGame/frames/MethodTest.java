package com.example.practice.bowlingGame.frames;

import com.example.practice.bowlingGame.FrameStatus;
import com.example.practice.bowlingGame.Frames;
import com.example.practice.bowlingGame.Roll;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MethodTest {

    private Frames frames;

    @Before
    public void setUp(){
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


}
