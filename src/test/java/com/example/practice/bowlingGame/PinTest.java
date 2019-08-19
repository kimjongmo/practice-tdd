package com.example.practice.bowlingGame;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PinTest {

    private Pin pin;

    @Before
    public void setUp(){
        pin = new Pin();
    }

    @Test
    public void 생성_시_핀의_개수가_10개인지(){
        assertEquals(10,pin.getRemain());
    }

    @Test
    public void 체크함수_범위를_벗어난_값일_때(){
        assertFalse(pin.checkScope(11));
    }
    @Test
    public void 체크함수_범위_안의_값(){
        assertTrue(pin.checkScope(6));
    }

    @Test(expected = ArithmeticException.class)
    public void 범위를_벗어난_핀을_쓰려뜨렸을_때_예외발생(){
        int knockDown = 11;
        pin.knockDown(knockDown);
    }

    @Test
    public void 범위_안에서_핀을_쓰려뜨렸을_때_거터(){
        int knockDown = 0;

        pin.knockDown(knockDown);

        assertEquals(10,pin.getRemain());
    }

    @Test
    public void 범위_안에서_핀을_쓰려뜨렸을_때_노말(){
        int knockDown = 6;

        pin.knockDown(knockDown);

        assertEquals(4,pin.getRemain());
    }

    @Test
    public void 범위_안에서_핀을_쓰려뜨렸을_때_스트라이크(){
        int knockDown = 10;
        pin.knockDown(knockDown);
        assertEquals(0,pin.getRemain());
    }

    @Test(expected = ArithmeticException.class)
    public void 첫번째는_범위_안의_값_두번째는_범위_밖에_값(){
        int first = 6,second=5;
        pin.knockDown(first);
        pin.knockDown(second);
    }

    @Test
    public void 첫번째_두번째_모두_정상_값(){
        int first =6,second=4;
        pin.knockDown(first);
        pin.knockDown(second);
        assertEquals(0,pin.getRemain());
    }


}
