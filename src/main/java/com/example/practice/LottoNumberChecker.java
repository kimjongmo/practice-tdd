package com.example.practice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LottoNumberChecker {

    Logger log = LoggerFactory.getLogger(LottoNumberChecker.class);

    public int check(int[] numbers) {


        int res = 0;

        if (!isCount(numbers))
            return -1;

        numberSort(numbers);

        if (!isNumberCheck(numbers))
            return -2;

        int[] winningNumber = generateWinningNumber();

//        return matching(numbers, winningNumber);
        return matching(numbers,new int[]{1,2,3,4,5,7,6});
    }


    public boolean isCount(int[] numbers) {
        if (numbers == null || numbers.length != 6) {
            return false;
        }
        return true;
    }

    public boolean isNumberCheck(int[] numbers) {

        for (int i = 0; i <= 5; i++) {
            if (numbers[i] < 1 || numbers[i] > 45) {
                return false;
            }
            if (i != 5 && numbers[i] == numbers[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public void numberSort(int[] numbers) {
        Arrays.sort(numbers);
    }

    public int[] generateWinningNumber() {

        Set<Integer> generate = new HashSet<>();
        int[] winningNumber = new int[7];
        int count = 7;

        while (count > 0) {
            int number = (int) ((Math.random() * 45) + 1);
            if (!generate.contains(number)) {
                generate.add(number);
                winningNumber[count - 1] = number;
                count--;
            }
        }

        log.info("당첨 범호 : {}", Arrays.toString(winningNumber));

        return winningNumber;
    }

    public int matching(int[] numbers, int[] winningNumber) {

        int cnt = 0, bonus = 0, result = 6;

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (numbers[i] == winningNumber[j]) {
                    if (j == 6)
                        bonus++;
                    else cnt++;
                }

            }
        }
        switch (cnt) {
            case 6:
                result = 1;
                break;
            case 5:
                if (bonus == 1)
                    result = 2;
                else
                    result = 3;
                break;
            case 4:
                result = 4;
                break;
            case 3:
                result = 5;
                break;
        }
        log.info("로또 결과 : {}등", result);
        return result;
    }
}
