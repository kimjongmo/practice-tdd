package com.example.practice.lotto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class LottoNumberChecker {

    Logger log = LoggerFactory.getLogger(LottoNumberChecker.class);

    public int check(int[] numbers) {

        if (!isCount(numbers))
            return -1;

        if (!isNonDuplicate(numbers))
            return -2;

        if (!isBetweenScope(numbers))
            return -2;

        return matching(numbers, LottoMachine.generate());
//        return matching(numbers,new int[]{1,2,3,4,5,7,6});
    }


    public boolean isCount(int[] numbers) {
        if (numbers == null || numbers.length != 6) {
            return false;
        }
        return true;
    }

    public boolean isNonDuplicate(int[] numbers) {
        if ((int) Arrays.stream(numbers).distinct().count() != numbers.length)
            return false;
        return true;
    }

    public boolean isBetweenScope(int[] numbers) {
        //숫자 범위 체크
        for (int i = 0; i <= 5; i++) {
            if (numbers[i] < 1 || numbers[i] > 45) {
                return false;
            }
        }
        return true;
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
