package com.example.practice.bowlingGame;

public class Game {
    private Player[] playerArr;
    private Frames[] frameArr;
    private int turn;
    private Pin pin;

    public Game(int count) {
        playerArr = new Player[count];
        frameArr = new Frames[count];
        turn = 0;
        pin = new Pin();
    }

    public void gamePlay() {

        Player player = playerArr[turn];
        Frames frames = frameArr[turn];
        int knockDown = player.rolling(pin.getRemain());
        boolean isTurnEnd = frames.record(knockDown);
        if (isTurnEnd) {
            turn += 1;
            turn %= playerArr.length;
            pin = new Pin();
        }
    }
}
