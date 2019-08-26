package com.example.practice.bowlingGame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Game {
    private Logger log = LoggerFactory.getLogger(Game.class);
    private Player[] playerArr; //플레이어
    private Frames[] frameArr;  //프레임
    private int turn;   //현재 플레이어를 나타내는 인덱스
    private Pin pin;    //볼링핀

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
        while (!isGameEnd(turn)) {
            log.info("현재 플레이어:{}", turn);

            Player player = playerArr[turn];
            Frames frames = frameArr[turn];

            int knockDown = player.rolling(pin.getRemain());
            pin.knockDown(knockDown);

            nextTurn(frames.record(knockDown));
        }
    }

    public boolean isGameEnd(int turn) {
        return frameArr[turn].nowFrame == 11 && turn == 0;
    }

    public void nextTurn(boolean isTurnEnd) {
        if (isTurnEnd) {
            turn += 1;
            turn %= playerArr.length;
            pinSetting();
        }
    }

    public void pinSetting() {
        pin = new Pin();
        log.info("----------------------------------------------------------");
    }
}
