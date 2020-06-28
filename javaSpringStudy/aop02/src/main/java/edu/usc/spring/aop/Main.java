package edu.usc.spring.aop;

import edu.usc.spring.aop.service.MyCalculator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Description TODO
 * @Author EricGao
 * @Date 10:17 2020/6/9
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext acpc = new AnnotationConfigApplicationContext(JavaConfig.class);
        MyCalculator myCalculator  = acpc.getBean(MyCalculator.class); //通过接口获取 bean
        myCalculator.add(3, 7);
        myCalculator.min(3, 7);
    }
}

