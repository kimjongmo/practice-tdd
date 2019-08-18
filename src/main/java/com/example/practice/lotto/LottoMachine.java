package com.example.practice.lotto;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LottoMachine {
    private static Logger log = LoggerFactory.getLogger(LottoMachine.class);
    private static final int LOTTO_BALL_COUNT = 7;
    private static List<Integer> machine = null;

    public static void machineSetting() {
        machine = new ArrayList<>();

        for (int i = 1; i < 46; i++) {
            machine.add(i);
        }
    }

    public static void machineOff() {
        machine = null;
    }

    public static int[] generate() {
        int[] winNumber = new int[LOTTO_BALL_COUNT];

        machineSetting();

        int i = 0;
        while (i < LOTTO_BALL_COUNT) {
            int index = (int) (Math.random() * machine.size()) + 1;
            winNumber[i++] = machine.remove(index-1);
        }

        machineOff();

        log.info("Generated : {}", Arrays.toString(winNumber));

        return winNumber;
    }

}
