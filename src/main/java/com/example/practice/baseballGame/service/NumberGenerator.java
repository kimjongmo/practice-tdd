package com.example.practice.baseballGame.service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import com.example.practice.baseballGame.model.BaseballNumber;

public class NumberGenerator {

    public BaseballNumber generate(){
        Integer[] numbers = random();
        boolean isDup = isDuplicate(numbers);
        if(!isDup)
            return BaseballNumber.of(numbers);
        return generate();
    }

    public Integer[] random() {
        Integer[] numbers = new Integer[3];
        Random random = new Random();
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(9) + 1;
        }
        return numbers;
    }

    public boolean isDuplicate(Integer[] numbers) {
        List<Integer> list = Arrays.asList(numbers);
        long nonDuplicatedNumber = list.stream().distinct().count();

        if (nonDuplicatedNumber == 3)
            return false;

        return true;
    }
}
