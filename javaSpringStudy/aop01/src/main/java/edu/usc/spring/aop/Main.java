package edu.usc.spring.aop;

/**
 * @Description TODO
 * @Author EricGao
 * @Date 10:17 2020/6/9
 */
public class Main {
    public static void main(String[] args) {
        MyCalculatorImp myCalculatorImp = new MyCalculatorImp();
        MyCalculator myCalculator = (MyCalculator) CalculatorProxy.getInstance(myCalculatorImp);
        int add = myCalculator.add(3, 7);
        System.out.println("add = " + add);
    }
}

