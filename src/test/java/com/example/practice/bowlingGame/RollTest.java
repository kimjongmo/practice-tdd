package com.example.practice.bowlingGame;

import org.junit.Test;

import static org.junit.Assert.*;

public class RollTest {

    @Test(expected = IllegalArgumentException.class)
    public void Roll_범위_밖의_값() {
        new Roll(11);
    }

    @Test(expected = IllegalArgumentException.class)
    public void Roll_음수_값() {
        new Roll(-1);
    }

    @Test
    public void Roll_정상적인_생성() {
        int knockDown = 5;
        Roll roll = new Roll(knockDown);
        assertEquals(knockDown, roll.getKnockDown());
    }
}
