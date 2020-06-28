package edu.usc.spring.ioc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description TODO
 * @Author EricGao
 * @Date 19:41 2020/6/7
 */
public class Main {
    public static void main(String[] args) {
//        method1();
//        method2();
//        method3();
//        method4();

    }

    /**
     * Java和XML混合配置
     */
    private static void method4() {
        AnnotationConfigApplicationContext acpc = new AnnotationConfigApplicationContext(JavaConfig2.class);
        DataSource ds = acpc.getBean("ds", DataSource.class);
        System.out.println("ds = " + ds);
    }

    /**
     * Profile (XML配置)
     */
    private static void method3() {
        ClassPathXmlApplicationContext cpac = new ClassPathXmlApplicationContext();
        cpac.getEnvironment().setActiveProfiles("dev");
        cpac.setConfigLocation("applicationContext.xml");
        cpac.refresh();
        DataSource ds = cpac.getBean(DataSource.class);
        System.out.println("ds = " + ds);
    }

    /**
     * Profile (java 配置)
     */
    private static void method2() {
        AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext();
        acac.getEnvironment().setActiveProfiles("prod");  //此处相当于开关的作用 dev对应开发环境，prod对应生产环境
        acac.register(JavaConfig.class);
        acac.refresh();
        DataSource ds = acac.getBean(DataSource.class);
        System.out.println("ds = " + ds);
    }

    /**
     * Condition 注解使用效果测试方法
     */
    private static void method1() {
        AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext(JavaConfig.class);
        ShowCmd cmd = (ShowCmd) acac.getBean("cmd");
        String cmdStr = cmd.showCmd();
        System.out.println("cmdStr = " + cmdStr);
    }
}
