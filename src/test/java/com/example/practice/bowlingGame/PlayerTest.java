package com.example.practice.bowlingGame;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static org.junit.Assert.*;

public class PlayerTest {

    private Logger log = LoggerFactory.getLogger(PlayerTest.class);
    private Player player;

    @Before
    public void setUp() {
        player = new Player();
    }

    @Test
    public void 랜덤함수_테스트() {
        int remain = (int)(Math.random()*11);

        int knockDown = player.rolling(remain);
        log.info("남은 핀 :{},쓰러뜨린 핀 수 :{}",remain,knockDown);

        assertTrue(0 <= knockDown && knockDown <= remain);
    }

}
