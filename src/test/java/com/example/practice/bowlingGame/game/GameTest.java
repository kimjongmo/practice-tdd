package com.example.practice.bowlingGame.game;

import com.example.practice.bowlingGame.Game;
import org.junit.Test;

public class GameTest {

    @Test
    public void 게임_시뮬레이션_테스트() {
        Game game = new Game(2);
        game.gamePlay();
    }
}
