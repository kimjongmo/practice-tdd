package com.example.practice;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class StringCalculator {

    private static Logger logger = LoggerFactory.getLogger(StringCalculator.class);

    /**
     * @param text Text is must consist of two number and sign.
     */
    public static int cal(String text) {

        int operationIndex = -1;

        checkFormat(text);

        operationIndex = findOpIndex(text);

        int[] parseNum = parseNum(text,operationIndex);

        return Calculator.cal(parseNum,text.charAt(operationIndex));
    }

    private static int findOpIndex(String text) {
        char[] arr = text.toCharArray();
        int temp = 0;

        while ('0' <= arr[temp++] && arr[temp] <= '9') ;

        return temp - 1;
    }

    private static void checkFormat(String text) {

        if (Strings.isEmpty(text)) {
            throw new ArithmeticException("빈문자 혹은 널값은 불가합니다.");
        }

        if (!text.matches("^([0-9]+)([+]|[-]|[/]|[*])([0-9]+)$")) {
            throw new ArithmeticException("적절하지 않은 포맷입니다.");
        }
    }

    private static int[] parseNum(String text, int operationIndex){
        int[] numbers = new int[2];

        numbers[0] = Integer.parseInt(text.substring(0,operationIndex));
        numbers[1] = Integer.parseInt(text.substring(operationIndex+1));

        return  numbers;
    }

}
