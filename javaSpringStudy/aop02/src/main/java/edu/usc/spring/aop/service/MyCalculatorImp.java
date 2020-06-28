package edu.usc.spring.aop.service;

import edu.usc.spring.aop.Action;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author EricGao
 * @Date 10:08 2020/6/9
 */
@Component
public class MyCalculatorImp implements MyCalculator{

//    @Action
    public Integer add(int a, int b) {
//        int i = 1 / 0;
        System.out.println(a + "+" + b + "=" + (a + b));
        return a + b;
    }

//    @Action
    public void min(int a, int b) {
        System.out.println(a + "-" + b + "=" + (a - b));
    }
}
