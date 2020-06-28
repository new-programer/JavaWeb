package edu.usc.aware;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

/**
 * @Description TODO
 * @Author EricGao
 * @Date 22:28 2020/6/8
 */
public class Main {
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext(JavaConfig.class);
        AwareService bean = acac.getBean(AwareService.class);
        bean.print();
    }
}
