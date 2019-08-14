package com.example.practice;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
public class CalculatorTest{

    @Test(expected = ArithmeticException.class)
    public void 널값_테스트(){
        StringCalculator.cal(null);
    }

    @Test(expected = ArithmeticException.class)
    public void 빈_문자열_테스트(){
        StringCalculator.cal("");
    }

    @Test
    public void 더하기() throws ArithmeticException{
        assertEquals("더하기 계산 실패",25,StringCalculator.cal("12+13"));
    }

    @Test
    public void 빼기()  throws ArithmeticException{
        assertEquals("빼기 계산 실패",1,StringCalculator.cal("13-12"));
    }

    @Test
    public void 곱하기()  throws ArithmeticException{
        assertEquals("곱하기 계산 실패",4,StringCalculator.cal("2*2"));
    }

    @Test
    public void 나누기()  throws ArithmeticException{
        assertEquals("나누기 계산 실패",2,StringCalculator.cal("4/2"));
    }

    @Test(expected = ArithmeticException.class)
    public void 기호가_여러개(){
        StringCalculator.cal("12++12");
    }

    @Test(expected = ArithmeticException.class)
    public void 숫자가_하나(){
        StringCalculator.cal("12+");
    }

    @Test(expected = ArithmeticException.class)
    public void 기호가_없을_때(){
        StringCalculator.cal("123");
    }

    @Test(expected = ArithmeticException.class)
    public void 숫자나_기호가_아닌_문자(){
        StringCalculator.cal("12+plus12");
    }

}
