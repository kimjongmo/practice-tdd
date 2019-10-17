package com.example.practice.baseballGame.model;

public class BaseballNumber {

    private int first, second, third;

    public BaseballNumber(int first, int second, int third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public static BaseballNumber of(Integer[] numbers) {
        return new BaseballNumber(numbers[0], numbers[1], numbers[2]);
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getThird() {
        return third;
    }

    public void setThird(int third) {
        this.third = third;
    }

    @Override
    public String toString() {
        return "BaseballNumber{" +
                "first=" + first +
                ", second=" + second +
                ", third=" + third +
                '}';
    }
}
