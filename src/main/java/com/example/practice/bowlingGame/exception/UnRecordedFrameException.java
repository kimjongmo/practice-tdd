package com.example.practice.bowlingGame.exception;

public class UnRecordedFrameException extends RuntimeException {
    public UnRecordedFrameException() {
    }
    public UnRecordedFrameException(String message) {
        super(message);
    }
}
