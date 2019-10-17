package com.example.practice.baseballGame;

import com.example.practice.baseballGame.model.BaseballNumber;
import com.example.practice.baseballGame.service.NumberGenerator;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class NumberGeneratorTest {

    private NumberGenerator generator;
    @Before
    public void init(){
        generator = new NumberGenerator();
    }

    @Test
    public void 생성된_숫자가_3개인지_테스트(){
        //조건
        Integer[] numbers;

        //행위
        numbers = generator.random();

        //결과
        assertThat(numbers.length).isEqualTo(3);
    }

    @Test
    public void 생성된_숫자가_중복되는_숫자를_포함할_때_테스트(){
        //조건
        Integer[] numbers = new Integer[]{1,2,1};

        //행위
        boolean isDup = generator.isDuplicate(numbers);

        assertThat(isDup).isTrue();
    }
    @Test
    public void 생성된_숫자가_중복되는_숫자를_포함하지_않을_때_테스트(){
        //조건
        Integer[] numbers = new Integer[]{1,2,3};
        //행위
        boolean isDup = generator.isDuplicate(numbers);

        assertThat(isDup).isFalse();
    }

    @Test
    public void 생성_테스트(){
        for(int i=0;i<10;i++){
            System.out.println(generator.generate());
        }
    }


}
