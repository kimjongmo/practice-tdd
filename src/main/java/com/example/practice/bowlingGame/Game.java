package com.example.practice.bowlingGame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Game {
    private Logger log = LoggerFactory.getLogger(Game.class);
    private Player[] playerArr;
    private Frames[] frameArr;
    private int turn;
    private Pin pin;

    public Game(int count) {
        playerArr = new Player[count];
        frameArr = new Frames[count];
        for (int i = 0; i < count; i++) {
            playerArr[i] = new Player();
            frameArr[i] = new Frames();
        }
        turn = 0;
        pin = new Pin();
    }

    public void gamePlay() {
        while (true) {
            log.info("현재 플레이어:{}", turn);
            Player player = playerArr[turn];
            Frames frames = frameArr[turn];
            int knockDown = player.rolling(pin.getRemain());
            pin.knockDown(knockDown);
            boolean isTurnEnd = frames.record(knockDown);
            if (isTurnEnd) {
                turn += 1;
                turn %= playerArr.length;
                pin = new Pin();
                log.info("----------------------------------------------------------");
            }
            if (isTurnEnd && frames.nowFrame == 11 && turn == 0)
                break;
        }
    }
}
