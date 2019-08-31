package com.example.practice.lotto;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class LottoTest {

    private LottoNumberChecker lottoNumberChecker;

    @Before
    public void init() {
        lottoNumberChecker = new LottoNumberChecker();
    }

    @Test
    public void test_널값_입력() {
        assertEquals(-1, lottoNumberChecker.check(null));
    }

    @Test
    public void test_6개_이하의_수() {
        assertEquals(-1, lottoNumberChecker.check(new int[]{}));
    }

    @Test
    public void test_6개_이상의_수(){
        assertEquals(-1,lottoNumberChecker.check(new int[]{1,2,3,4,5,6,7}));
    }

    @Test
    public void test_중복되는_수가_존재() {
        assertEquals(-2, lottoNumberChecker.check(new int[]{1, 2, 3, 4, 5, 5}));
    }

    @Test
    public void test_범위를_넘어가는_수() {
        assertEquals(-2, lottoNumberChecker.check(new int[]{8, 10, 19, 22, 43, 47}));
    }

    @Test
    public void test_성공_케이스() {
        int result = lottoNumberChecker.check(new int[]{17, 22, 3, 44, 35, 10});
        assertTrue(0 <= result && result < 7);
    }


}
